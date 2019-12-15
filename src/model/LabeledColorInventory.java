package model;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LabeledColorInventory {

	private Map<Integer, LabeledColor> colorMap;
	private Map<Integer, LabeledColor> approximateColorMap;

	public LabeledColorInventory() {
		colorMap = new HashMap<Integer, LabeledColor>();
		approximateColorMap = new HashMap<Integer, LabeledColor>();
	}

	public void add(LabeledColor labeledColor) {
		colorMap.put(labeledColor.toInteger(), labeledColor);
	}

	public void add(Collection<LabeledColor> labeledColors) {
		for (LabeledColor labeledColor : labeledColors)
			this.add(labeledColor);
	}

	public LabeledColor getLabeledColor(int red, int green, int blue) {
		return colorMap.get((red << 16) + (green << 8) + blue);
	}

	public LabeledColor getClosestLabeledColor(int red, int green, int blue) {
		LabeledColor color = approximateColorMap.get((red << 16) + (green << 8) + blue);

		if (color == null) {

			LabeledColor closestColor = null;

			int smallestDifference = -1;
			for (LabeledColor existingColor : colorMap.values()) {

				int redDiff = red - existingColor.getRed();
				int greenDiff = green - existingColor.getGreen();
				int blueDiff = blue - existingColor.getBlue();
				int difference = redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff;

				if (closestColor == null) {
					closestColor = existingColor;
					smallestDifference = difference;

				} else {
					if (difference < smallestDifference) {
						closestColor = existingColor;
						smallestDifference = difference;
					}
				}
			}

			color = new LabeledColor(closestColor.getName(), red, green, blue);
			approximateColorMap.put(color.toInteger(), color);
		}

		return color;
	}

}
