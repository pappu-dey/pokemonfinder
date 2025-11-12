package com.dsarp.pocamon;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PokemonResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("sprites")
    private Sprites sprites;

    @SerializedName("types")
    private List<TypeWrapper> types;

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<TypeWrapper> getTypes() {
        return types;
    }

    public static class Sprites {
        @SerializedName("front_default")
        private String frontDefault;

        @SerializedName("other")
        private Other other;

        public String getFrontDefault() {
            return frontDefault;
        }

        public Other getOther() {
            return other;
        }
    }

    public static class Other {
        @SerializedName("official-artwork")
        private OfficialArtwork officialArtwork;

        public OfficialArtwork getOfficialArtwork() {
            return officialArtwork;
        }
    }

    public static class OfficialArtwork {
        @SerializedName("front_default")
        private String frontDefault;

        public String getFrontDefault() {
            return frontDefault;
        }
    }

    public static class TypeWrapper {
        @SerializedName("type")
        private Type type;

        public Type getType() {
            return type;
        }
    }

    public static class Type {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }
}
