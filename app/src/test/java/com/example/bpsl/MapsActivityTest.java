package com.example.bpsl;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class MapsActivityTest {

    MapsActivity test = new MapsActivity();

    @Test
    public void testGetUrl() {
        String expectedURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-37.0901266,174.9929603&radius=10000&type=grocery_or_supermarket&sensor=true&name=countdown&keyword=countdown&key=AIzaSyB4QKrFVweZXJN-gHiMl6lpYFASMAQ9-04";
        assertEquals(expectedURL, test.getUrl(-37.0901266,174.9929603,"countdown"));
    }
}
