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
package net.imglib2.type.numeric.integer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnsignedIntTypeTest
{
	/**
	 * Regression test that verifies in range long values work as expected when
	 * passed to {@link UnsignedIntType#UnsignedIntType(long)}.
	 */
	@Test
	public void testInRangeValues()
	{
		final long l = 9823409L;
		final UnsignedIntType u = new UnsignedIntType( l );

		assertEquals( l, u.get() );

		u.set( 0L );
		assertEquals( 0L, u.get() );

		u.set( 120L );
		assertEquals( 120L, u.get() );
	}

	/**
	 * Regression test that verifies that an long which is greater than 
	 * 0xffffffffL works as expected when passed to
	 * {@link UnsignedIntType#UnsignedIntType(long)}.
	 */
	@Test
	public void testPositiveOutOfRangeValue()
	{
		final long l = 4294967299L;
		final UnsignedIntType u = new UnsignedIntType( l );

		assertEquals( 3L, u.get() );
	}

	/**
	 * Regression test that verifies that a negative value works as
	 * expected when passed to {@link UnsignedIntType#UnsignedIntType(long)}.
	 */
	@Test
	public void testNegativeOutOfRangeValue()
	{
		final long l = -2038L;
		final UnsignedIntType u = new UnsignedIntType( l );

		assertEquals( 4294965258L, u.get() );
	}
}
