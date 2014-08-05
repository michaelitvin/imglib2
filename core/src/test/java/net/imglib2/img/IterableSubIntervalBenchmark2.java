package net.imglib2.img;

import net.imglib2.Cursor;
import net.imglib2.FinalInterval;
import net.imglib2.Interval;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.img.planar.PlanarImg;
import net.imglib2.img.planar.PlanarImgs;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.view.Views;

/*
 * Benchmarked 20.06.2013  Thinkpad i7-2620M CPU @ 2.7 GHz
 * 
 * 	Planar Image: the optimized cursor is slower when executed after Array cursors, but faster by  ~factor 100 if executed
 * 				  on its own. This may be due to JIT confusion.
 *  Array Image: the optimized cursor is faster by ~factor 100
 */
public class IterableSubIntervalBenchmark2
{

	/**
	 * Walk through an Interval on an Img using a LocalizingCursor, localizing
	 * on every step.
	 * 
	 * @param img
	 * @param interval
	 */
	protected static void localizingWalkThrough1( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).localizingCursor();
		final long[] pos = new long[ interval.numDimensions() ];
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
			c.localize( pos );
		}
		j = ( int ) pos[ 0 ] + i;
	}

	// exact copy of localizingWalkThrough1
	protected static void localizingWalkThrough2( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).localizingCursor();
		final long[] pos = new long[ interval.numDimensions() ];
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
			c.localize( pos );
		}
		j = ( int ) pos[ 0 ] + i;
	}

	// exact copy of localizingWalkThrough1
	protected static void localizingWalkThrough3( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).localizingCursor();
		final long[] pos = new long[ interval.numDimensions() ];
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
			c.localize( pos );
		}
		j = ( int ) pos[ 0 ] + i;
	}

	// exact copy of localizingWalkThrough1
	protected static void localizingWalkThrough4( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).localizingCursor();
		final long[] pos = new long[ interval.numDimensions() ];
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
			c.localize( pos );
		}
		j = ( int ) pos[ 0 ] + i;
	}

	/**
	 * Walk through an Interval on an Img using a Cursor
	 * 
	 * @param img
	 * @param interval
	 */
	protected static void walkThrough1( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).cursor();
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
		}
		j = i;
	}

	// exact copy of walkThrough1
	protected static void walkThrough2( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).cursor();
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
		}
		j = i;
	}

	// exact copy of walkThrough1
	protected static void walkThrough3( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).cursor();
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
		}
		j = i;
	}

	// exact copy of walkThrough1
	protected static void walkThrough4( final Img< IntType > img, final Interval interval )
	{
		final Cursor< IntType > c = Views.interval( img, interval ).cursor();
		int i = 0;
		while ( c.hasNext() )
		{
			c.fwd();
			i += c.get().get();
		}
		j = i;
	}

	public static int j;

	public static void main( final String[] args )
	{
		final int numRuns = 20;
		final boolean printIndividualTimes = false;

		final long[] dimensions = new long[] { 5000, 5000, 2, 2 };

		// doesn't fit the interval, will force unoptimized cursor
		final long[] dimensionsUnoptimized = new long[] { 5001, 5000, 2, 2 };

		// fits the interval, should be optimized
		final Interval interval = new FinalInterval( new long[] { 0, 0, 1, 1 }, new long[] { 4999, 4999, 1, 1 } );

		// create and fill images
		final ArrayImg< IntType, ? > arrayImg = ArrayImgs.ints( dimensions ); // fits
																				// the
																				// interval
		// doesn't fit the interval
		final ArrayImg< IntType, ? > arrayImgUnOp = ArrayImgs.ints( dimensionsUnoptimized );

		// fits the interval
		final PlanarImg< IntType, ? > planarImg = PlanarImgs.ints( dimensions );

		// doesn't fit the interval
		final PlanarImg< IntType, ? > planarImgUnOp = PlanarImgs.ints( dimensionsUnoptimized );

		testArrayImg( numRuns, printIndividualTimes, interval, arrayImg, arrayImgUnOp );
		testPlanarImg( numRuns, printIndividualTimes, interval, planarImg, planarImgUnOp );
		testArrayImg( numRuns, printIndividualTimes, interval, arrayImg, arrayImgUnOp );
		testPlanarImg( numRuns, printIndividualTimes, interval, planarImg, planarImgUnOp );
		testArrayImg( numRuns, printIndividualTimes, interval, arrayImg, arrayImgUnOp );
		testPlanarImg( numRuns, printIndividualTimes, interval, planarImg, planarImgUnOp );
	}

	/*
	 * the 2nd img is unoptimized with respect to the provided interval i.e.
	 * while optimized cursors can be used for the first image given interval
	 * this is not possible for the 2nd one.
	 */
	protected static void testArrayImg( final int numRuns, final boolean printIndividualTimes, final Interval interval, final ArrayImg< IntType, ? > arrayImg, final ArrayImg< IntType, ? > arrayImgUnOp )
	{

		// BLOCK 1

		System.out.println( "normal cursor | array img" );
		System.out.println( "walk through a subinterval" );
		IterableSubIntervalBenchmark.benchmarkAndCompare( numRuns, printIndividualTimes, new Runnable()
		{
			@Override
			public void run()
			{
				walkThrough1( arrayImgUnOp, interval );
			}
		}, new Runnable()
		{
			@Override
			public void run()
			{
				walkThrough2( arrayImg, interval );
			}
		} );

		// BLOCK 2

		System.out.println( "localizing cursor | array img" );
		System.out.println( "walk through a subinterval" );
		IterableSubIntervalBenchmark.benchmarkAndCompare( numRuns, printIndividualTimes, new Runnable()
		{
			@Override
			public void run()
			{
				localizingWalkThrough1( arrayImgUnOp, interval );
			}
		}, new Runnable()
		{
			@Override
			public void run()
			{
				localizingWalkThrough2( arrayImg, interval );
			}
		} );
	}

	/*
	 * the 2nd img is unoptimized with respect to the provided interval i.e.
	 * while optimized cursors can be used for the first image given inter this
	 * is not possible for the 2nd one.
	 */
	protected static void testPlanarImg( final int numRuns, final boolean printIndividualTimes, final Interval interval, final PlanarImg< IntType, ? > planarImg, final PlanarImg< IntType, ? > planarImgUnOp )
	{

		// BLOCK 1

		System.out.println( "normal cursor | planar img" );
		System.out.println( "walk through a subinterval" );
		IterableSubIntervalBenchmark.benchmarkAndCompare( numRuns, printIndividualTimes, new Runnable()
		{
			@Override
			public void run()
			{
				walkThrough3( planarImgUnOp, interval );
			}
		}, new Runnable()
		{
			@Override
			public void run()
			{
				walkThrough4( planarImg, interval );
			}
		} );

		// BLOCK 2

		System.out.println( "localizing cursor | planar img" );
		System.out.println( "walk through a subinterval" );
		IterableSubIntervalBenchmark.benchmarkAndCompare( numRuns, printIndividualTimes, new Runnable()
		{
			@Override
			public void run()
			{
				localizingWalkThrough3( planarImgUnOp, interval );
			}
		}, new Runnable()
		{
			@Override
			public void run()
			{
				localizingWalkThrough4( planarImg, interval );
			}
		} );
	}

}
