package net.imglib2.ops.features.firstorder.sums;

import java.util.Iterator;

import net.imglib2.ops.features.AbstractFeature;
import net.imglib2.ops.features.RequiredFeature;
import net.imglib2.ops.features.providers.GetIterableInterval;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;


public class Sum<T extends RealType<T>> extends AbstractFeature<DoubleType> {

    @RequiredFeature
    private GetIterableInterval<T> interval;

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return "Sum";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sum<T> copy() {
        return new Sum<T>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleType recompute() {
        double result = 0.0f;

        Iterator<T> it = interval.get().iterator();
        while (it.hasNext()) {
            result += it.next().getRealDouble();
        }

        return new DoubleType(result);
    }

}
