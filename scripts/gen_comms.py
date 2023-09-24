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
            else:
                out += f"""
    public int read{capitalize(datatype)}{capitalize(field)}(int slot) {{
        return uc.read({fields_so_far} + slot * {len(SCHEMA[datatype]['fields'])});
    }}

    public void write{capitalize(datatype)}{capitalize(field)}(int slot, int value) {{
        uc.write({fields_so_far} + slot * {len(SCHEMA[datatype]['fields'])}, value);
    }}
"""
            fields_so_far += 1

    print(f"Total fields used: {fields_so_far}")
    if fields_so_far > 1000000:
        raise Exception("Too many fields")
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
                # elif '// PRIORITY SECTOR INIT' in line:
                #     f.write(gen_init_sectors())
                # elif '// SECTOR CONTROL STATUS RESET' in line:
                #     f.write(gen_sector_control_status_reset())
                else:
                    f.write(line)
