package com.wraitho.arch;

public enum MarvelImageVariants {

	PORTRAIT_SMALL("portrait_small", 50, 75),
	PORTRAIT_MEDIUM("portrait_medium", 100, 150),
	PORTRAIT_XLARGE("portrait_xlarge", 150, 225),
	PORTRAIT_FANTASTIC("portrait_fantastic", 168, 252),
	PORTRAIT_UNCANNY("portrait_uncanny", 300, 450),
	PORTRAIT_INCREDIBLE("portrait_incredible", 216, 324),

	STANDARD_SMALL("standard_small",	65, 45),
	STANDARD_MEDIUM("standard_medium", 100, 100),
	STANDARD_LARGE("standard_large", 140, 140),
	STANDARD_XLARGE("standard_xlarge", 200, 200),
	STANDARD_FANTASTIC("standard_fantastic", 250, 250),
	STANDARD_AMAZING("standard_amazing", 180, 180),

	LANDSCAPE_SMALL("landscape_small", 120, 90),
	LANDSCAPE_MEDIUM("landscape_medium", 175, 130),
	LANDSCAPE_LARGE("landscape_large", 190, 140),
	LANDSCAPE_XLARGE("landscape_xlarge", 270, 200),
	LANDSCAPE_AMAZING("landscape_amazing", 250, 156),
	LANDSCAPE_INCREDIBLE("landscape_incredible", 464, 261);

	String urlVariant;
	int width;
	int height;

	MarvelImageVariants(String urlVariant, int width, int height) {
		this.urlVariant = urlVariant;
		this.width = width;
		this.height = height;
	}

	public String getUrlVariant() {
		return urlVariant;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
