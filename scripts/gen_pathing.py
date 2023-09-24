#! /usr/bin/env python3

import sys
from pathlib import Path
from math import *

def encode(x, y):
    return (x+7) + 15*(y+7)

RADII = {'32': 32, '20': 20, '10': 10}
SMALLER_RADII = {'32': 20, '20': 10, '10': 5}

DIRECTIONS = {
    (1, 0): 'EAST',
    (-1, 0): 'WEST',
    (0, 1): 'NORTH',
    (0, -1): 'SOUTH',
    (1, 1): 'NORTHEAST',
    (-1, 1): 'NORTHWEST',
    (1, -1): 'SOUTHEAST',
    (-1, -1): 'SOUTHWEST',
}

# Max val of 5
def binary_search(indent, strVal, val_dict):
    return f"""
if ({strVal} >= 0) {{
    if ({strVal} >= 3) {{
        if ({strVal} == 3) {{
            {val_dict[3]}
        }} else if ({strVal} == 4) {{
            {val_dict[4]}
        }} else {{
            {val_dict[5]}
        }}
    }} else {{
        if ({strVal} == 0) {{
            {val_dict[0]}
        }} else if ({strVal} == 1) {{
            {val_dict[1]}
        }} else {{
            {val_dict[2]}
        }}
    }}
}} else {{
    if ({strVal} >= -2) {{
        if ({strVal} == -1) {{
            {val_dict[-1]}
        }} else {{
            {val_dict[-2]}
        }}
    }} else {{
        if ({strVal} == -3) {{
            {val_dict[-3]}
        }} else if ({strVal} == -4) {{
            {val_dict[-4]}
        }} else {{
            {val_dict[-5]}
        }}
    }}
}}
"""


def min_chain(vals):
    if len(vals) == 1:
        return vals[0]
    return f"Math.min({vals[0]}, {min_chain(vals[1:])})"

def opposite(dir):
    if dir == 'EAST':
        return 'WEST'
    if dir == 'WEST':
        return 'EAST'
    if dir == 'NORTH':
        return 'SOUTH'
    if dir == 'SOUTH':
        return 'NORTH'
    if dir == 'NORTHEAST':
        return 'SOUTHWEST'
    if dir == 'NORTHWEST':
        return 'SOUTHEAST'
    if dir == 'SOUTHEAST':
        return 'NORTHWEST'
    if dir == 'SOUTHWEST':
        return 'NORTHEAST'
    return 'ZERO'

def direction_to(dx, dy):
    if (abs(dx) >= 2.414 * abs(dy)):
        if (dx > 0):
            return "EAST"
        elif (dx < 0):
            return "WEST"
        else:
            return "ZERO"
    elif (abs(dy) >= 2.414 * abs(dx)):
        if (dy > 0):
            return "NORTH"
        else:
            return "SOUTH"
    else:
        if (dy > 0):
            if (dx > 0):
                return "NORTHEAST"
            else:
                return "NORTHWEST"
        else:
            if (dx > 0):
                return "SOUTHEAST"
            else:
                return "SOUTHWEST"

def dist(x, y):
    return x*x + y*y

def gen_constants(radius):
    out = f""""""
    for x in range(-7, 8):
        for y in range(-7, 8):
            if dist(x, y) <= radius:
                out += f"""
    Location l{encode(x,y)}; // location representing relative coordinate ({x}, {y})
    double d{encode(x,y)}; // shortest distance to location from current location
    // Direction dir{encode(x,y)}; // best direction to take now to optimally get to location
    double score{encode(x,y)}; // heuristic distance from location to target
"""
    return out

def sign(x):
    if x > 0:
        return 1
    if x < 0:
        return -1
    return 0

def gen_init(radius):
    out = f"""
        l{encode(0,0)} = uc.getLocation();
        d{encode(0,0)} = 0;
        // dir{encode(0,0)} = ZERO;
"""
    for r2 in range(1, radius+1):
        for x in range(-7, 8):
            for y in range(-7, 8):
                if dist(x, y) == r2:
                    out += f"""
        l{encode(x,y)} = l{encode(x - sign(x), y - sign(y))}.add({DIRECTIONS[(sign(x), sign(y))]}); // ({x}, {y}) from ({x - sign(x)}, {y - sign(y)})
        d{encode(x,y)} = 99999;
        // dir{encode(x,y)} = null;
"""
    return out

def gen_bfs(radius):
    visited = set([encode(0,0)])
    out = f"""
"""
    for r2 in range(1, radius+1):
        for x in range(-7, 8):
            for y in range(-7, 8):
                if dist(x, y) == r2:
                    out += f"""
        // check ({x}, {y})"""
                    indent = ""
                    out += f"""
        if (uc.canSenseLocation(l{encode(x,y)}) && 
                (mapObj = uc.senseObjectAtLocation(l{encode(x,y)}, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) {{ """
                    indent = "    "
                    dxdy = [(dx, dy) for dx in range(-1, 2) for dy in range(-1, 2) if (dx, dy) != (0, 0) and dist(x+dx,y+dy) <= radius]
                    dxdy = sorted(dxdy, key=lambda dd: dist(x+dd[0], y+dd[1]))
                    vals = []
                    for dx, dy in dxdy:
                        if encode(x+dx, y+dy) in visited:
                            vals.append(str([1/16, 5/16, 2/16, 6/16, 3/16, 7/16, 4/16, 8/16][(round(atan2(-dy,-dx)/pi*4)+8)%8]) if (x+dx,y+dy) == (0, 0) else f'd{encode(x+dx,y+dy)}{"" if dx * dy == 0 else f" + 4"}')
                    out += f"""
        {indent}d{encode(x,y)} = 10 + {min_chain(vals)};"""
                    out += f"""
        }}"""
                    visited.add(encode(x,y))
                    out += "\n"
    return out

def gen_selection(radius, smaller_radius):
    out = f"""
        if (target.distanceSquared(l{encode(0,0)}) <= {radius}) {{
            int target_dx = target.x - l{encode(0,0)}.x;
            int target_dy = target.y - l{encode(0,0)}.y;"""
    val_dict_x = {}
    for tdx in range(-7, 8):
        if tdx**2 <= radius:
            val_dict_y = {}
            for tdy in range(-7, 8):
                if dist(tdx, tdy) <= radius:
                    val_dict_y[tdy] = f"return direction(d{encode(tdx, tdy)}); // destination is at relative location ({tdx}, {tdy})"
                else:
                    val_dict_y[tdy] = f"""uc.println("BFS: Invalid loc"); return null;"""
            val_dict_x[tdx] = binary_search("                ", "target_dy", val_dict_y)
        else:
            val_dict_x[tdx] = f"""uc.println("BFS: Invalid loc"); return null;"""
    
    out += binary_search("            ", "target_dx", val_dict_x)

    out += f"""
        }}
        
        ans = Double.POSITIVE_INFINITY;
        bestScore = 0;
        currDist = Math.sqrt(l{encode(0,0)}.distanceSquared(target));
        """
    for x in range(-7, 8):
        for y in range(-7, 8):
            if smaller_radius < dist(x, y) <= radius: # on the edge of the radius radius
                out += f"""
        score{encode(x,y)} = (currDist - Math.sqrt(l{encode(x,y)}.distanceSquared(target))) / d{encode(x,y)};
        if (score{encode(x,y)} > bestScore) {{
            bestScore = score{encode(x,y)};
            ans = d{encode(x,y)};
        }}
"""
    return out

def gen_print(radius):
    out = f"""
        // System.out.println("LOCAL DISTANCES:");"""
    for y in range(7, -8, -1):
        if y**2 <= radius:
            out += f"""
        // System.out.println("""
            for x in range(-7, 8):
                if dist(x, y) <= radius:
                    out += f""""\\t" + d{encode(x,y)} + """
                else:
                    out += f""""\\t" + """
            out = out[:-3] + """);"""
    out += f"""
        // System.out.println("DIRECTIONS:");"""
    for y in range(7, -8, -1):
        if y**2 <= radius:
            out += f"""
        // System.out.println("""
            for x in range(-7, 8):
                if dist(x, y) <= radius:
                    out += f""""\\t" + dir{encode(x,y)} + """
                else:
                    out += f""""\\t" + """
            out = out[:-3] + """);"""
    return out

def gen_full(bot, rad):
    radius = RADII[rad]
    smaller_radius = SMALLER_RADII[rad]
    out_file = Path('./src/') / bot / f'bfs/BFS{rad}.java'
    with open(out_file, 'w') as f:
        f.write(f"""package {bot}.bfs;

import aic2023.user.*;

public class BFS{rad} {{

    public UnitController uc;
{gen_constants(radius)}

    public BFS{rad}(UnitController r) {{
        uc = r;
    }}

    private final Direction[] DIRECTIONS = new Direction[] {{null, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHWEST, Direction.SOUTHEAST, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH}};

    public final Direction NORTH = Direction.NORTH;
    public final Direction NORTHEAST = Direction.NORTHEAST;
    public final Direction EAST = Direction.EAST;
    public final Direction SOUTHEAST = Direction.SOUTHEAST;
    public final Direction SOUTH = Direction.SOUTH;
    public final Direction SOUTHWEST = Direction.SOUTHWEST;
    public final Direction WEST = Direction.WEST;
    public final Direction NORTHWEST = Direction.NORTHWEST;
    public final Direction ZERO = Direction.ZERO;

    public double ans;
    public double bestScore;
    public double currDist;
    public MapObject mapObj;

    public Direction direction(double dist) {{
        if (dist==Double.POSITIVE_INFINITY) {{
            return null;
        }}
        return DIRECTIONS[(int)(dist * 16 % 16)];
    }}

    public Direction bestDir(Location target) {{
{gen_init(radius)}
{gen_bfs(radius)}
{gen_print(radius)}
{gen_selection(radius, smaller_radius)}
        
        return direction(ans);
    }}
}}
""")

if __name__ == '__main__':
    if len(sys.argv) == 1:
        for rad in ["32", "20", "10"]:
            gen_full("MPWorking", rad)
    elif len(sys.argv) == 2:
        for rad in ["32", "20", "10"]:
            gen_full(sys.argv[1], rad)
    else:
        gen_full(sys.argv[1], sys.argv[2])
