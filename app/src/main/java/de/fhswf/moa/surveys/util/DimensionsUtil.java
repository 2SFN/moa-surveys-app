package de.fhswf.moa.surveys.util;

import android.content.res.Resources;
import android.util.TypedValue;

import androidx.annotation.NonNull;

/**
 * Einfache Utility-Klasse zur Umrechnung von Bildschirm-Einheiten.
 */
public final class DimensionsUtil {

    private DimensionsUtil() {
    }

    public static float dpToPx(@NonNull Resources resources, float dip) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip, resources.getDisplayMetrics()
        );
    }

}
