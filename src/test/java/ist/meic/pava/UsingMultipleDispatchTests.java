package ist.meic.pava;

import ist.meic.pava.MultipleDispatch.UsingMultipleDispatch;
import ist.meic.pava.MultipleDispatchExtended.MultipleDispatchCacheSingleton;
import ist.meic.pava.MultipleDispatchExtended.UsingMultipleDispatchExtended;
import ist.meic.pava.domainExample.testSuite.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@SpringBootTest
class UsingMultipleDispatchTests {

    @DataProvider(name = "doubleDispatchData")
    private Object[][] doubleDispatchData(){
        return new Object[][] {
                {new Screen(), new Line(), "Screen.draw(Line)"},
                {new Screen(), new Circle(), "Screen.draw(Circle)"},
                {new Printer(), new Line(), "Printer.draw(Line)"},
                {new Printer(), new Circle(), "Printer.draw(Circle)"},};
    }

    @DataProvider(name = "tripleDispatchData")
    private Object[][] tripleDispatchData(){
        return new Object[][] {
                {new Screen(), new Line(), new Pencil(), "Screen.draw(Line, Pencil)"},
                {new Screen(), new Circle(), new Pencil(), "Screen.draw(Circle, Pencil)"},
                {new Printer(), new Line(), new Pencil(), "Printer.draw(Line, Pencil)"},
                {new Printer(), new Circle(), new Pencil(), "Printer.draw(Circle, Pencil)"},
                {new Screen(), new Line(), new Crayon(), "Screen.draw(Line, Crayon)"},
                {new Screen(), new Circle(), new Crayon(), "Screen.draw(Circle, Crayon)"},
                {new Printer(), new Line(), new Crayon(), "Printer.draw(Line, Crayon)"},
                {new Printer(), new Circle(), new Crayon(), "Printer.draw(Circle, Crayon)"},};
    }

    @DataProvider(name = "quadrupleDispatchData")
    private Object[][] quadrupleDispatchData(){
        return new Object[][] {
                {new Screen(), new Line(), new Pencil(), new Thin(), "Screen.draw(Line, Pencil, Thin)"},
                {new Screen(), new Circle(), new Pencil(), new Thin(), "Screen.draw(Circle, Pencil, Thin)"},
                {new Printer(), new Line(), new Pencil(), new Thin(), "Printer.draw(Line, Pencil, Thin)"},
                {new Printer(), new Circle(), new Pencil(), new Thin(), "Printer.draw(Circle, Pencil, Thin)"},
                {new Screen(), new Line(), new Crayon(), new Thin(), "Screen.draw(Line, Crayon, Thin)"},
                {new Screen(), new Circle(), new Crayon(), new Thin(), "Screen.draw(Circle, Crayon, Thin)"},
                {new Printer(), new Line(), new Crayon(), new Thin(), "Printer.draw(Line, Crayon, Thin)"},
                {new Printer(), new Circle(), new Crayon(), new Thin(), "Printer.draw(Circle, Crayon, Thin)"},
                {new Screen(), new Line(), new Pencil(), new Fat(), "Screen.draw(Line, Pencil, Fat)"},
                {new Screen(), new Circle(), new Pencil(), new Fat(), "Screen.draw(Circle, Pencil, Fat)"},
                {new Printer(), new Line(), new Pencil(), new Fat(), "Printer.draw(Line, Pencil, Fat)"},
                {new Printer(), new Circle(), new Pencil(), new Fat(), "Printer.draw(Circle, Pencil, Fat)"},
                {new Screen(), new Line(), new Crayon(), new Fat(), "Screen.draw(Line, Crayon, Fat)"},
                {new Screen(), new Circle(), new Crayon(), new Fat(), "Screen.draw(Circle, Crayon, Fat)"},
                {new Printer(), new Line(), new Crayon(), new Fat(), "Printer.draw(Line, Crayon, Fat)"},
                {new Printer(), new Circle(), new Crayon(), new Fat(), "Printer.draw(Circle, Crayon, Fat)"},};
    }

    // UsingMultipleDispatch.class tests

    @Test (dataProvider = "doubleDispatchData")
    public void doubleDispatchTest(Device device, Shape shape, String expectedResult) {
        //Act
        String result = (String) UsingMultipleDispatch.invoke(device, "draw", shape);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test (dataProvider = "tripleDispatchData")
    public void tripleDispatchTest(Device device, Shape shape, Brush brush, String expectedResult) {
        //Act
        String result = (String) UsingMultipleDispatch.invoke(device, "draw", shape, brush);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test (dataProvider = "quadrupleDispatchData")
    public void quadrupleDispatchTest(Device device, Shape shape, Brush brush, StrokeWidth strokeWidth, String expectedResult) {
        //Act
        String result = (String) UsingMultipleDispatch.invoke(device, "draw", shape, brush, strokeWidth);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void specialNonVariableArityTest() {
        //Arrange
        Screen device = new Screen();
        Integer argument1 = Integer.valueOf(1);
        Float argument2 = Float.valueOf(1);
        String expectedResult = device.poing(argument1, argument2);

        //Act
        String result = (String) UsingMultipleDispatch.invoke(device, "poing", argument1, argument2);

        //Assert
        assertEquals(result, expectedResult);
    }


    // UsingMultipleDispatchExtended.class tests

    @Test (dataProvider = "doubleDispatchData")
    public void doubleDispatchExtendedTest(Device device, Shape shape, String expectedResult) {
        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "draw", shape);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test (dataProvider = "tripleDispatchData")
    public void tripleDispatchExtendedTest(Device device, Shape shape, Brush brush, String expectedResult) {
        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "draw", shape, brush);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test (dataProvider = "quadrupleDispatchData")
    public void quadrupleDispatchExtendedTest(Device device, Shape shape, Brush brush, StrokeWidth strokeWidth, String expectedResult) {
        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "draw", shape, brush, strokeWidth);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void specialNonVariableArityExtendedTest() {
        //Arrange
        Screen device = new Screen();
        Integer argument1 = Integer.valueOf(1);
        Float argument2 = Float.valueOf(1);
        String expectedResult = device.poing(argument1, argument2);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1, argument2);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void variableArityTestI(){
        //Arrange
        Screen device = new Screen();
        Integer argument1 = Integer.valueOf(1);
        Long argument2 = Long.valueOf(1);
        Float argument3 = Float.valueOf(1);
        String argument4 = "ola";
        String expectedResult = device.poing(argument1, argument2, argument3, argument4);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1, argument2, argument3, argument4);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void variableArityTestII(){
        //Arrange
        Screen device = new Screen();
        Integer argument1 = Integer.valueOf(1);
        Long argument2 = Long.valueOf(1);
        Float argument3 = Float.valueOf(1);
        Double argument4 = Double.valueOf(1.0);
        String argument5 = "ola";
        String expectedResult = device.poing(argument1, argument2, argument3, argument4, argument5);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1, argument2, argument3, argument4, argument5);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void variableArityTestIII(){
        //Arrange
        Screen device = new Screen();
        Integer argument1 = Integer.valueOf(1);
        Long argument2 = Long.valueOf(1);
        Float argument3 = Float.valueOf(1);
        Double argument4 = Double.valueOf(1.0);
        Double argument5 = Double.valueOf(1.0);
        String expectedResult = device.poing(argument1, argument2, argument3, argument4, argument5);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1, argument2, argument3, argument4, argument5);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void variableArityTestIV(){
        //Arrange
        Screen device = new Screen();
        Long argument1 = Long.valueOf(1);
        String expectedResult = device.poing(argument1);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void variableArityTestV(){
        //Arrange
        Screen device = new Screen();
        String argument1 = "ola";
        String expectedResult = device.poing(argument1);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void variableArityTestVI(){
        //Arrange
        Screen device = new Screen();
        Line argument1 = new Line();
        Pencil argument2 = new Pencil();
        Fat argument3 = new Fat();
        String argument4 = "ola";
        Integer argument5 = Integer.valueOf(1);
        String expectedResult = device.poing(argument1, argument2, argument3, argument4, argument5);

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1, argument2, argument3, argument4, argument5);

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void javaSemanticDifferenceTest(){
        //Arrange
        Device device = new Device();
        Object[] argument1 = new Object[]{1,2,3};
        String expectedResult = "Device.poing(Integer, Number...)";

        //Act
        String result = (String) UsingMultipleDispatchExtended.invoke(device, "poing", argument1);

        //Assert
        assertEquals(result, expectedResult);
    }

}
