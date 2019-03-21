
import itertools
import io
import json
import os
import sys

DEBUG_MODE = json.loads(sys.argv[1].lower())


def log(s: str):
    print("  >> %s" % s)


class KeyHeight:
    def __init__(self, name: str, height_level: int) -> None:
        self.name = name
        self.id = str(height_level).lower()
        self.value = "key_height_" + name
        self.info = "@dimen/%s (Lv.%d)" % (self.value, height_level)


class FooterHeight:
    def __init__(self, name: str, height_level: int) -> None:
        self.name = name
        alphabets = ["a", "b", "c", "d", "e"]
        self.id = alphabets[height_level - 1]
        self.value = "footer_height_" + name
        self.info = "@dimen/%s (Lv.%s)" % (self.value, height_level)


class Adjustment:
    def __init__(self, is_right: bool) -> None:
        self.name = "right" if is_right else "left"
        self.id = self.name[0:1]
        self.info = "for " + self.name + " hand"


key_heights = [
    KeyHeight("small", 1),
    KeyHeight("semi_small", 2),
    KeyHeight("medium", 3),
    KeyHeight("semi_large", 4),
    KeyHeight("large", 5)
]

footer_heights = [
    FooterHeight("small", 1),
    FooterHeight("semi_small", 2),
    FooterHeight("medium", 3),
    FooterHeight("semi_large", 4),
    FooterHeight("large", 5)
]

adjustments = [
    Adjustment(False),
    Adjustment(True)
]


if DEBUG_MODE:
    orders = [(KeyHeight("td", 0), FooterHeight("tf", 3), Adjustment(True))]
else:
    orders = list(itertools.product(key_heights, footer_heights, adjustments))

current_dir = os.path.dirname(os.path.abspath(__file__))
output_dir = "output/keyboard/xml/"
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

os.chdir(current_dir)

for (kh, fh, adj) in orders:
    template = io.open(
        "%s/template/keyboard_%s_hand.xml" % (current_dir, adj.name))
    newtext = ""
    for newline in template:
        newline = newline.replace("$KEY_HEIGHT_INFO", kh.info)
        newline = newline.replace("$KEY_HEIGHT_VALUE", kh.value)
        newline = newline.replace("$FOOTER_INFO", fh.info)
        newline = newline.replace("$FOOTER_VALUE", fh.value)
        newline = newline.replace("$ADJUSTMENT_INFO", adj.info)
        newtext += newline
    template.close()
    # filename example: keyboard_b3_right.xml
    filename = "keyboard_%s%s_%s.xml" % (fh.id, kh.id, adj.name)
    newxml = io.open(output_dir + filename, "w+")
    newxml.write(newtext)
    newxml.close()
    log("generate: ./" + output_dir + filename)
print()
