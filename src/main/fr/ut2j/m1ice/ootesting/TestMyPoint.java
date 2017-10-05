package main.fr.ut2j.m1ice.ootesting;

import static org.junit.Assert.*;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMyPoint {
	private Double d1, d2;
	private MyPoint point1;
	private MyPoint point2;
	private MyPoint point3;
	private MyPoint point4;
	private MyPoint pointScale;
	private Random r1, r2;
	private ITranslation translation;

	@Before
	public void setUp() throws Exception {
		d1 = 2d;
		d2 = 4d;
		point1 = new MyPoint();
		point2 = new MyPoint(d1, d2);
		point3 = new MyPoint(point2);
		point4 = new MyPoint(null);
		pointScale = point2.scale(2);
		
		r1 = EasyMock.createMock(Random.class);
		EasyMock.expect(r1.nextInt()).andReturn(3);
		EasyMock.replay(r1);
		r2 = EasyMock.createMock(Random.class);
		EasyMock.expect(r2.nextInt()).andReturn(5);
		EasyMock.replay(r2);
		
		translation = EasyMock.createMock(ITranslation.class);
		EasyMock.expect(translation.getTx()).andReturn(23);
		EasyMock.expect(translation.getTy()).andReturn(27);
		EasyMock.replay(translation);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetSetX() {
		point2.setX(5.32d);
		assertEquals(5.32d, point2.getX(), 0.0001);
	}
	
	@Test
	public void testGetSetY() {
		point2.setY(75.15d);
		assertEquals(75.15d, point2.getY(), 0.0001);
	}
	
	@Test
	public void testGetSetNanX() {
		point2.setX(Double.NaN);
		assertEquals(d1, point2.getX(), 0.0001);
	}
	
	@Test
	public void testGetSetNanY() {
		point2.setY(Double.NaN);
		assertEquals(d2, point2.getY(), 0.0001);
	}

	@Test
	public void testConstructor1X() {
		assertEquals(0d, point1.getX(), 0.0001);
	}
	
	@Test
	public void testConstructor1Y() {
		assertEquals(0d, point1.getY(), 0.0001);
	}
	
	@Test
	public void testConstructor2X() {
		assertEquals(d1, point2.getX(), 0.0001);
	}
	
	@Test
	public void testConstructor2Y() {
		assertEquals(d2, point2.getY(), 0.0001);
	}
	
	@Test
	public void testConstructor3X() {
		assertEquals(d1, point3.getX(), 0.0001);
	}

	@Test
	public void testConstructor3Y() {
		assertEquals(d2, point3.getY(), 0.0001);
	}
	
	@Test
	public void testConstructor3NullX() {
		assertEquals(0d, point4.getX(), 0.0001);
	}

	@Test
	public void testConstructor3NullY() {
		assertEquals(0d, point4.getY(), 0.0001);
	}
	
	@Test
	public void testScaleX() {
		assertEquals(4d, pointScale.getX(), 0.0001);
	}

	@Test
	public void testScaleY() {
		assertEquals(8d, pointScale.getY(), 0.0001);
	}
	
	@Test
	public void testHorizontalSymetrieX() {
		point2 = point2.horizontalSymmetry(pointScale);
		assertEquals(6d, point2.getX(), 0.0001);
	}
	
	@Test
	public void testHorizontalSymetrieY() {
		point2 = point2.horizontalSymmetry(pointScale);
		assertEquals(4d, point2.getY(), 0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHorizontalSymetrieNull() {
		point2 = point2.horizontalSymmetry(null);
	}

	@Test
	public void testMiddleX() {
		point2 = point2.getMiddlePoint(pointScale);
		assertEquals(3d, point2.getX(), 0.0001);
	}

	@Test
	public void testMiddleY() {
		point2 = point2.getMiddlePoint(pointScale);
		assertEquals(6d, point2.getY(), 0.0001);
	}
	
	@Test
	public void testTranslateX() {
		point2.translate(d1, d2);
		assertEquals(4d, point2.getX(), 0.0001);
	}
	
	@Test
	public void testTranslateY() {
		point2.translate(d1, d2);
		assertEquals(8d, point2.getY(), 0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCentralSymmetryNull() {
	    new MyPoint(10 , 20).centralSymmetry(null) ;
	}
	
	@Test
	public void testSetPointX() {
		point1.setPoint(r1, r2);
		assertEquals(3, point1.getX(), 0.0001);
	}
	
	@Test
	public void testSetPointY() {
		point1.setPoint(r1, r2);
		assertEquals(5, point1.getY(), 0.0001);
	}
	
	@Test
	public void testTranslationX() {
		point2.translate(translation);
		assertEquals(25, point2.getX(), 0.0001);
	}
	
	@Test
	public void testTranslationY() {
		point2.translate(translation);
		assertEquals(31, point2.getY(), 0.0001);
	}
	
	@Test
	public void testTranslationNullX() {
		point2.translate(null);
		assertEquals(2, point2.getX(), 0.0001);
	}
	
	@Test
	public void testTranslationNullY() {
		point2.translate(null);
		assertEquals(4, point2.getY(), 0.0001);
	}
}
