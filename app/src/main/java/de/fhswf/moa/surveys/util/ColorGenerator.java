package de.fhswf.moa.surveys.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Klasse, die basierend auf dem HashCode eines Objektes eine Farbe aus einer vorgegebenen
 * Liste auswählt.
 *
 * Angepasste Version, Quelle Original:
 * https://github.com/amulyakhare/TextDrawable/blob/master/library/src/main/java/com/amulyakhare/textdrawable/util/ColorGenerator.java
 *
 * @author amulya
 */
public class ColorGenerator {

    public static ColorGenerator DEFAULT;

    public static ColorGenerator MATERIAL;

    static {
        DEFAULT = create(Arrays.asList(
                0xfff16364,
                0xfff58559,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff2093cd,
                0xffad62a7,
                0xff805781
        ));

        MATERIAL = create(Arrays.asList(
                //rot
                0xffF46D61,
                0xfff06292, // stärker

                0xffba68c8, // Lila
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
               // 0xff4fc3f7, // auch Türkis
                0xff4dd0e1, // Türkis
                0xff4db6ac,
               // 0xff81c784, leicht zu helles grün
                0xffff8a65,
               // 0xffffb74d, Gelb zu hell
               // 0xffa1887f, braun
               // 0xff90a4ae, // Blau silber misch
                0xff00655B,  // grün dunkel
                0xffB7950B, // gelb dunkel
                0xff28B463, // grün kräftig ++
                0xff5EA09C, // Blau

                0xfff49448 // oranges gelb
        ));
    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
}
