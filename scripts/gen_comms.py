#! /usr/bin/env python3

from pathlib import Path
import sys, os

SCHEMA = {
    'map': {
        'slots': 1,
        'fields': [
            'x_min',
            'x_max',
            'width',
            'y_min',
            'y_max',
            'height',
        ]
    },
    'HQ': {
        'slots': 1,
        'fields': [
            'flag',
            'x_coord',
            'y_coord',
        ]
    },
    'symmetry': {
        'slots': 1,
        'fields': [
            'vertical',
            'horizontal',
            'rotational',
        ]
    },
    'base': {
        'slots': 16,
        'fields': [
            'x',
            'y',
        ],
        'is_loc': True,
    },
    'stadium': {
        'slots': 16,
        'fields': [
            'x',
            'y',
        ],
        'is_loc': True,
    },
    'num': {
        'slots': 1,
        'fields': [
            'pitchers',
            'pitchers_last',
            'batters',
            'batters_last',
            'catchers',
            'catchers_last',
        ]
    }
}

def capitalize(s):
    return ''.join(x.capitalize() for x in s.split('_'))


def gen_constants():
    out = """"""
    for datatype in SCHEMA:
        out += f"""
    public final int {datatype.upper()}_SLOTS = {SCHEMA[datatype]['slots']};"""
    return out+"\n"


def gen():
    out = """"""""
    fields_so_far = 0
    for datatype in SCHEMA:
        for field in SCHEMA[datatype]['fields']:
            if SCHEMA[datatype]['slots'] == 1:
                out += f"""
    public int read{capitalize(datatype)}{capitalize(field)}() {{
        return uc.read({fields_so_far});
    }}

    public void write{capitalize(datatype)}{capitalize(field)}(int value) {{
        uc.write({fields_so_far}, value);
    }}
"""
                if datatype == 'num' and not field.endswith('_last'):
                    out += f"""
    public void increment{capitalize(field)}() {{
        write{capitalize(datatype)}{capitalize(field)}(read{capitalize(datatype)}{capitalize(field)}() + 1);
    }}

    public void reset{capitalize(field)}() {{
        write{capitalize(datatype)}{capitalize(field)}(0);
    }}
"""
            else:
                out += f"""
    public int read{capitalize(datatype)}{capitalize(field)}(int slot) {{
        return uc.read({fields_so_far} + slot * {len(SCHEMA[datatype]['fields'])});
    }}

    public void write{capitalize(datatype)}{capitalize(field)}(int slot, int value) {{
        uc.write({fields_so_far} + slot * {len(SCHEMA[datatype]['fields'])}, value);
    }}
"""
            fields_so_far += SCHEMA[datatype]['slots']
        if 'is_loc' in SCHEMA[datatype] and SCHEMA[datatype]['is_loc']:
            out += f"""
    public Location read{capitalize(datatype)}(int slot) {{
        return new Location(read{capitalize(datatype)}X(slot), read{capitalize(datatype)}Y(slot));
    }}

    public void write{capitalize(datatype)}(int slot, Location loc) {{
        write{capitalize(datatype)}X(slot, loc.x);
        write{capitalize(datatype)}Y(slot, loc.y);
    }}

    public void log{capitalize(datatype)}(Location loc) {{
        int slot = -1;
        Location slotLoc;
        for (; ++slot < {datatype.upper()}_SLOTS;) {{
            slotLoc = read{capitalize(datatype)}(slot);
            if (slotLoc.x == -1) {{
                write{capitalize(datatype)}(slot, loc);
                uc.println("Logging {datatype} at " + loc + " in slot " + slot);
                return;
            }} else if (slotLoc.equals(loc)) {{
                return;
            }}
        }}
    }}
"""

    print(f"Total fields used: {fields_so_far}")
    if fields_so_far > 1000000:
        raise Exception("Too many fields")
    return out


def gen_init_loc_methods():
    out = """"""
    for datatype in SCHEMA:
        if 'is_loc' in SCHEMA[datatype] and SCHEMA[datatype]['is_loc']:
            out += f"""
    public void init{capitalize(datatype)}() {{
        for (int i = {datatype.upper()}_SLOTS; --i >= 0;) {{
            write{capitalize(datatype)}(i, new Location(-1, -1));
        }}
    }}
"""
    return out


def gen_init_method_calls():
    out = """"""
    for datatype in SCHEMA:
        if 'is_loc' in SCHEMA[datatype] and SCHEMA[datatype]['is_loc']:
            out += f"""            init{capitalize(datatype)}();
"""
    return out


if __name__ == '__main__':
    main_dir = Path(__file__).parent.parent

    package_name = len(sys.argv) > 1 and sys.argv[1] or 'MPWorking'
    template_file = main_dir / 'templates/CommsTemplate.java'
    out_file = main_dir / 'src' / package_name / 'Comms.java'
    with open(template_file, 'r') as t:
        with open(out_file, 'w') as f:
            for line in t:
                if 'package examplefuncsplayer;' in line:
                    f.write(f"package {package_name};\n")
                elif '// MAIN READ AND WRITE METHODS' in line:
                    f.write(gen())
                elif '// CONSTS' in line:
                    f.write(gen_constants())
                elif '// INIT LOC METHODS' in line:
                    f.write(gen_init_loc_methods())
                elif '// INIT METHOD CALLS' in line:
                    f.write(gen_init_method_calls())
                else:
                    f.write(line)
