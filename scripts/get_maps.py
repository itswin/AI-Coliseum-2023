#!/usr/bin/env python3

# Credit to https://github.com/jmerle

import requests
from pathlib import Path

def main() -> None:
    maps_dir = Path(__file__).parent.parent / "maps"

    list_response = requests.get("https://www.coliseum.ai/api/tournaments/aic2023/maps")
    list_response.raise_for_status()

    for obj in list_response.json():
        if not obj["visible"]:
            continue

        map_name = obj["name"]
        print(f"Downloading {map_name}")

        map_response = requests.get(f"https://www.coliseum.ai/api/tournaments/aic2023/maps/{map_name.lower()}/file")
        map_response.raise_for_status()

        map_file = maps_dir / f"{map_name}.txt"
        with map_file.open("wb+") as file:
            file.write(map_response.content)

if __name__ == "__main__":
    main()
