package com.wraitho.arch.feature.characters.recyclerview.character;

import com.wraitho.arch.MarvelImageVariants;

public class CharacterListItem {

    private final String imageUrl;

    public CharacterListItem(String imagePath, String extension, MarvelImageVariants variant) {
        this.imageUrl = String.format("%s/%s.%s", imagePath, variant.getUrlVariant(), extension);
    }

	public String getImageUrl() {
		return imageUrl;
	}
}
