import java.awt.Color;
import java.util.Arrays;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private double energyTo[][];


	public SeamCarver(Picture picture1) {
		if (picture1 == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = picture1;
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
			return 1000;
		}
		Color left = picture.get(x, y - 1);
		Color right = picture.get(x, y + 1);
		Color top = picture.get(x - 1, y);
		Color bottom = picture.get(x + 1, y);
		int red = bottom.getRed() - top.getRed();
		int blue = bottom.getBlue() - top.getBlue();
		int green = bottom.getGreen() - top.getGreen();
		int vertical = red * red + blue * blue + green * green;
		int redv = left.getRed() - right.getRed();
		int bluev = left.getBlue() - right.getBlue();
		int greenv = left.getGreen() - right.getGreen();
		int horizontal = redv * redv + bluev * bluev + greenv * greenv;
		double enrgy = Math.sqrt(vertical + horizontal);
		return enrgy;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {

		return new int[0];
	}
	public double[][] getEnergies() {
		energyTo = new double[height()][width()];
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				if (i == 0 || j == 0 || height() - 1 == i || width() - 1 == j) {
					energyTo[i][j] = 1000;
				} else {
					energyTo[i][j] = energy(i, j);
				}
			}
		}
		return energyTo;
	}
	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int[] result = new int[width()];
		double[][] energyCopy = getEnergies();
		for (int i = 1; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				double minEnergy = -1;
				if (j == 0) {
					minEnergy = Math.min(energyCopy[i - 1][j + 1], energyCopy[i - 1][j]);
				} else if (j == width() - 1) {
					minEnergy = Math.min(energyCopy[i - 1][j - 1], energyCopy[i - 1][j]);
				} else {
					minEnergy = Math.min(Math.min(energyCopy[i - 1][j - 1], energyCopy[i - 1][j]),
					                     energyCopy[i - 1][j + 1]);
				}
				energyCopy[i][j] += minEnergy;
			}
		}
		for (int i = 1; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				double minimum = Integer.MAX_VALUE;
				if (energyCopy[i][j] < minimum) {
					minimum = energyCopy[i][j];
				}
			}
		}
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}