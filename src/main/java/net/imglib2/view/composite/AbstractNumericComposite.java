/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2015 Tobias Pietzsch, Stephan Preibisch, Barry DeZonia,
 * Stephan Saalfeld, Curtis Rueden, Albert Cardona, Christian Dietz, Jean-Yves
 * Tinevez, Johannes Schindelin, Jonathan Hale, Lee Kamentsky, Larry Lindsey, Mark
 * Hiner, Michael Zinsmaier, Martin Horn, Grant Harris, Aivar Grislis, John
 * Bogovic, Steffen Jaensch, Stefan Helfrich, Jan Funke, Nick Perry, Mark Longair,
 * Melissa Linkert and Dimiter Prodanov.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package net.imglib2.view.composite;

import java.util.Iterator;

import net.imglib2.RandomAccess;
import net.imglib2.type.numeric.NumericType;

/**
 * Abstract base class for a vector of {@link NumericType} scalars.  It is a
 * {@link NumericType} itself, implementing the {@link NumericType} algebra as
 * element-wise operations.
 *
 * @author Stephan Saalfeld <saalfelds@janelia.hhmi.org>
 */
abstract public class AbstractNumericComposite< T extends NumericType< T >, C extends AbstractNumericComposite< T, C > > extends AbstractComposite< T > implements NumericType< C >, Iterable< T >
{
	final protected int length;
	
	final protected Iterator< T > iterator = new Iterator< T >()
	{
		@Override
		public boolean hasNext()
		{
			return sourceAccess.getIntPosition( d ) + 1 < length;
		}

		@Override
		public T next()
		{
			sourceAccess.fwd( d );
			final T t = sourceAccess.get();
			return t;
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	};
	
	public AbstractNumericComposite( final RandomAccess< T > sourceAccess, final int length )
	{
		super( sourceAccess );
		this.length = length;
	}

	@Override
	public void set( final C c )
	{
		sourceAccess.setPosition( 0, d );
		c.sourceAccess.setPosition( 0, c.d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().set( c.sourceAccess.get() );
			sourceAccess.fwd( d );
			c.sourceAccess.fwd( c.d );
		}
	}

	@Override
	public void add( final C c )
	{
		sourceAccess.setPosition( 0, d );
		c.sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().add( c.sourceAccess.get() );
			sourceAccess.fwd( d );
			c.sourceAccess.fwd( d );
		}
	}

	@Override
	public void sub( final C c )
	{
		sourceAccess.setPosition( 0, d );
		c.sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().sub( c.sourceAccess.get() );
			sourceAccess.fwd( d );
			c.sourceAccess.fwd( d );
		}
	}

	@Override
	public void mul( final C c )
	{
		sourceAccess.setPosition( 0, d );
		c.sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().mul( c.sourceAccess.get() );
			sourceAccess.fwd( d );
			c.sourceAccess.fwd( d );
		}
	}

	@Override
	public void div( final C c )
	{
		sourceAccess.setPosition( 0, d );
		c.sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().div( c.sourceAccess.get() );
			sourceAccess.fwd( d );
			c.sourceAccess.fwd( d );
		}
	}

	@Override
	public void setZero()
	{
		sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().setZero();
			sourceAccess.fwd( d );
		}
	}

	@Override
	public void setOne()
	{
		sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().setOne();
			sourceAccess.fwd( d );
		}
	}

	@Override
	public void mul( final float c )
	{
		sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().mul( c );
			sourceAccess.fwd( d );
		}
	}

	@Override
	public void mul( final double c )
	{
		sourceAccess.setPosition( 0, d );
		while ( sourceAccess.getLongPosition( d ) < length )
		{
			sourceAccess.get().mul( c );
			sourceAccess.fwd( d );
		}
	}

	@Override
	public Iterator< T > iterator()
	{
		sourceAccess.setPosition( -1, d );
		return iterator;
	}
}
