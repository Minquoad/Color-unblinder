package model.standard;

import java.util.Locale;

import model.LabeledColor;
import model.LabeledColorInventory;

public class HtmlStandard extends Standard {

	public HtmlStandard(Locale locale) {
		super(locale);
	}

	@Override
	public String getId() {
		return "html";
	}

	@Override
	public String getName() {
		return "HTML";
	}

	@Override
	public void fillInventory(LabeledColorInventory inventory) {

		inventory.add(toLabeledColor("AliceBlue", "#F0F8FF"));
		inventory.add(toLabeledColor("AntiqueWhite", "#FAEBD7"));
		inventory.add(toLabeledColor("Aqua", "#00FFFF"));
		inventory.add(toLabeledColor("Aquamarine", "#7FFFD4"));
		inventory.add(toLabeledColor("Azure", "#F0FFFF"));
		inventory.add(toLabeledColor("Beige", "#F5F5DC"));
		inventory.add(toLabeledColor("Bisque", "#FFE4C4"));
		inventory.add(toLabeledColor("Black", "#000000"));
		inventory.add(toLabeledColor("BlanchedAlmond", "#FFEBCD"));
		inventory.add(toLabeledColor("Blue", "#0000FF"));
		inventory.add(toLabeledColor("BlueViolet", "#8A2BE2"));
		inventory.add(toLabeledColor("Brown", "#A52A2A"));
		inventory.add(toLabeledColor("BurlyWood", "#DEB887"));
		inventory.add(toLabeledColor("CadetBlue", "#5F9EA0"));
		inventory.add(toLabeledColor("Chartreuse", "#7FFF00"));
		inventory.add(toLabeledColor("Chocolate", "#D2691E"));
		inventory.add(toLabeledColor("Coral", "#FF7F50"));
		inventory.add(toLabeledColor("CornflowerBlue", "#6495ED"));
		inventory.add(toLabeledColor("Cornsilk", "#FFF8DC"));
		inventory.add(toLabeledColor("Crimson", "#DC143C"));
		inventory.add(toLabeledColor("Cyan", "#00FFFF"));
		inventory.add(toLabeledColor("DarkBlue", "#00008B"));
		inventory.add(toLabeledColor("DarkCyan", "#008B8B"));
		inventory.add(toLabeledColor("DarkGoldenRod", "#B8860B"));
		inventory.add(toLabeledColor("DarkGray", "#A9A9A9"));
		inventory.add(toLabeledColor("DarkGrey", "#A9A9A9"));
		inventory.add(toLabeledColor("DarkGreen", "#006400"));
		inventory.add(toLabeledColor("DarkKhaki", "#BDB76B"));
		inventory.add(toLabeledColor("DarkMagenta", "#8B008B"));
		inventory.add(toLabeledColor("DarkOliveGreen", "#556B2F"));
		inventory.add(toLabeledColor("DarkOrange", "#FF8C00"));
		inventory.add(toLabeledColor("DarkOrchid", "#9932CC"));
		inventory.add(toLabeledColor("DarkRed", "#8B0000"));
		inventory.add(toLabeledColor("DarkSalmon", "#E9967A"));
		inventory.add(toLabeledColor("DarkSeaGreen", "#8FBC8F"));
		inventory.add(toLabeledColor("DarkSlateBlue", "#483D8B"));
		inventory.add(toLabeledColor("DarkSlateGray", "#2F4F4F"));
		inventory.add(toLabeledColor("DarkSlateGrey", "#2F4F4F"));
		inventory.add(toLabeledColor("DarkTurquoise", "#00CED1"));
		inventory.add(toLabeledColor("DarkViolet", "#9400D3"));
		inventory.add(toLabeledColor("DeepPink", "#FF1493"));
		inventory.add(toLabeledColor("DeepSkyBlue", "#00BFFF"));
		inventory.add(toLabeledColor("DimGray", "#696969"));
		inventory.add(toLabeledColor("DimGrey", "#696969"));
		inventory.add(toLabeledColor("DodgerBlue", "#1E90FF"));
		inventory.add(toLabeledColor("FireBrick", "#B22222"));
		inventory.add(toLabeledColor("FloralWhite", "#FFFAF0"));
		inventory.add(toLabeledColor("ForestGreen", "#228B22"));
		inventory.add(toLabeledColor("Fuchsia", "#FF00FF"));
		inventory.add(toLabeledColor("Gainsboro", "#DCDCDC"));
		inventory.add(toLabeledColor("GhostWhite", "#F8F8FF"));
		inventory.add(toLabeledColor("Gold", "#FFD700"));
		inventory.add(toLabeledColor("GoldenRod", "#DAA520"));
		inventory.add(toLabeledColor("Gray", "#808080"));
		inventory.add(toLabeledColor("Grey", "#808080"));
		inventory.add(toLabeledColor("Green", "#008000"));
		inventory.add(toLabeledColor("GreenYellow", "#ADFF2F"));
		inventory.add(toLabeledColor("HoneyDew", "#F0FFF0"));
		inventory.add(toLabeledColor("HotPink", "#FF69B4"));
		inventory.add(toLabeledColor("IndianRed", "#CD5C5C"));
		inventory.add(toLabeledColor("Indigo", "#4B0082"));
		inventory.add(toLabeledColor("Ivory", "#FFFFF0"));
		inventory.add(toLabeledColor("Khaki", "#F0E68C"));
		inventory.add(toLabeledColor("Lavender", "#E6E6FA"));
		inventory.add(toLabeledColor("LavenderBlush", "#FFF0F5"));
		inventory.add(toLabeledColor("LawnGreen", "#7CFC00"));
		inventory.add(toLabeledColor("LemonChiffon", "#FFFACD"));
		inventory.add(toLabeledColor("LightBlue", "#ADD8E6"));
		inventory.add(toLabeledColor("LightCoral", "#F08080"));
		inventory.add(toLabeledColor("LightCyan", "#E0FFFF"));
		inventory.add(toLabeledColor("LightGoldenRodYellow", "#FAFAD2"));
		inventory.add(toLabeledColor("LightGray", "#D3D3D3"));
		inventory.add(toLabeledColor("LightGrey", "#D3D3D3"));
		inventory.add(toLabeledColor("LightGreen", "#90EE90"));
		inventory.add(toLabeledColor("LightPink", "#FFB6C1"));
		inventory.add(toLabeledColor("LightSalmon", "#FFA07A"));
		inventory.add(toLabeledColor("LightSeaGreen", "#20B2AA"));
		inventory.add(toLabeledColor("LightSkyBlue", "#87CEFA"));
		inventory.add(toLabeledColor("LightSlateGray", "#778899"));
		inventory.add(toLabeledColor("LightSlateGrey", "#778899"));
		inventory.add(toLabeledColor("LightSteelBlue", "#B0C4DE"));
		inventory.add(toLabeledColor("LightYellow", "#FFFFE0"));
		inventory.add(toLabeledColor("Lime", "#00FF00"));
		inventory.add(toLabeledColor("LimeGreen", "#32CD32"));
		inventory.add(toLabeledColor("Linen", "#FAF0E6"));
		inventory.add(toLabeledColor("Magenta", "#FF00FF"));
		inventory.add(toLabeledColor("Maroon", "#800000"));
		inventory.add(toLabeledColor("MediumAquaMarine", "#66CDAA"));
		inventory.add(toLabeledColor("MediumBlue", "#0000CD"));
		inventory.add(toLabeledColor("MediumOrchid", "#BA55D3"));
		inventory.add(toLabeledColor("MediumPurple", "#9370DB"));
		inventory.add(toLabeledColor("MediumSeaGreen", "#3CB371"));
		inventory.add(toLabeledColor("MediumSlateBlue", "#7B68EE"));
		inventory.add(toLabeledColor("MediumSpringGreen", "#00FA9A"));
		inventory.add(toLabeledColor("MediumTurquoise", "#48D1CC"));
		inventory.add(toLabeledColor("MediumVioletRed", "#C71585"));
		inventory.add(toLabeledColor("MidnightBlue", "#191970"));
		inventory.add(toLabeledColor("MintCream", "#F5FFFA"));
		inventory.add(toLabeledColor("MistyRose", "#FFE4E1"));
		inventory.add(toLabeledColor("Moccasin", "#FFE4B5"));
		inventory.add(toLabeledColor("NavajoWhite", "#FFDEAD"));
		inventory.add(toLabeledColor("Navy", "#000080"));
		inventory.add(toLabeledColor("OldLace", "#FDF5E6"));
		inventory.add(toLabeledColor("Olive", "#808000"));
		inventory.add(toLabeledColor("OliveDrab", "#6B8E23"));
		inventory.add(toLabeledColor("Orange", "#FFA500"));
		inventory.add(toLabeledColor("OrangeRed", "#FF4500"));
		inventory.add(toLabeledColor("Orchid", "#DA70D6"));
		inventory.add(toLabeledColor("PaleGoldenRod", "#EEE8AA"));
		inventory.add(toLabeledColor("PaleGreen", "#98FB98"));
		inventory.add(toLabeledColor("PaleTurquoise", "#AFEEEE"));
		inventory.add(toLabeledColor("PaleVioletRed", "#DB7093"));
		inventory.add(toLabeledColor("PapayaWhip", "#FFEFD5"));
		inventory.add(toLabeledColor("PeachPuff", "#FFDAB9"));
		inventory.add(toLabeledColor("Peru", "#CD853F"));
		inventory.add(toLabeledColor("Pink", "#FFC0CB"));
		inventory.add(toLabeledColor("Plum", "#DDA0DD"));
		inventory.add(toLabeledColor("PowderBlue", "#B0E0E6"));
		inventory.add(toLabeledColor("Purple", "#800080"));
		inventory.add(toLabeledColor("RebeccaPurple", "#663399"));
		inventory.add(toLabeledColor("Red", "#FF0000"));
		inventory.add(toLabeledColor("RosyBrown", "#BC8F8F"));
		inventory.add(toLabeledColor("RoyalBlue", "#4169E1"));
		inventory.add(toLabeledColor("SaddleBrown", "#8B4513"));
		inventory.add(toLabeledColor("Salmon", "#FA8072"));
		inventory.add(toLabeledColor("SandyBrown", "#F4A460"));
		inventory.add(toLabeledColor("SeaGreen", "#2E8B57"));
		inventory.add(toLabeledColor("SeaShell", "#FFF5EE"));
		inventory.add(toLabeledColor("Sienna", "#A0522D"));
		inventory.add(toLabeledColor("Silver", "#C0C0C0"));
		inventory.add(toLabeledColor("SkyBlue", "#87CEEB"));
		inventory.add(toLabeledColor("SlateBlue", "#6A5ACD"));
		inventory.add(toLabeledColor("SlateGray", "#708090"));
		inventory.add(toLabeledColor("SlateGrey", "#708090"));
		inventory.add(toLabeledColor("Snow", "#FFFAFA"));
		inventory.add(toLabeledColor("SpringGreen", "#00FF7F"));
		inventory.add(toLabeledColor("SteelBlue", "#4682B4"));
		inventory.add(toLabeledColor("Tan", "#D2B48C"));
		inventory.add(toLabeledColor("Teal", "#008080"));
		inventory.add(toLabeledColor("Thistle", "#D8BFD8"));
		inventory.add(toLabeledColor("Tomato", "#FF6347"));
		inventory.add(toLabeledColor("Turquoise", "#40E0D0"));
		inventory.add(toLabeledColor("Violet", "#EE82EE"));
		inventory.add(toLabeledColor("Wheat", "#F5DEB3"));
		inventory.add(toLabeledColor("White", "#FFFFFF"));
		inventory.add(toLabeledColor("WhiteSmoke", "#F5F5F5"));
		inventory.add(toLabeledColor("Yellow", "#FFFF00"));
		inventory.add(toLabeledColor("YellowGreen", "#9ACD32"));

	}

	private static LabeledColor toLabeledColor(String name, String hexCode) {

		String newName = name.substring(0, 1);

		for (int i = 1; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isLowerCase(c)) {
				newName += c;
			} else {
				newName += ' ';
				newName += Character.toLowerCase(c);
			}
		}

		return new LabeledColor(
				newName,
				Integer.parseInt(hexCode.substring(1, 3), 16),
				Integer.parseInt(hexCode.substring(3, 5), 16),
				Integer.parseInt(hexCode.substring(5, 7), 16));
	}

}
