package by.andersen.chronology.controller;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.service.TripService;

@AutoConfigureGraphQlTester
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TripControllerTest {

    @Autowired
    private GraphQlTester tester;

    @MockBean
    private TripService tripService;

    @Test
    void shouldGetAllTrips() {
        TripDto trip = TripDto.builder().id(UUID.randomUUID()).build();

        Mockito.when(tripService.getAllTrips()).thenReturn(List.of(trip));
        List<TripDto> trips = tester.document(
                """
                        query {
                            getAllTrips {
                                id
                                startDate
                                endDate
                                coordinates {
                                    latitude
                                    longitude
                                }
                                owner
                                guests
                            }
                        }
                        """)
                .execute()
                .path("data.getAllTrips")
                .entityList(TripDto.class)
                .get();

        Assertions.assertEquals(1, trips.size());
    }

    @Test
    void shouldGetTripById() {
        UUID id = UUID.randomUUID();
        TripDto tripDto = TripDto.builder().id(id).build();
        Mockito.when(tripService.getTripById(id)).thenReturn(tripDto);
        TripDto trip = tester.document(
                """
                        query {
                            getTripById(id: "%s") {
                                id
                                startDate
                                endDate
                                coordinates {
                                    latitude
                                    longitude
                                }
                                owner
                                guests
                            }
                        }
                        """.formatted(id))
                .execute()
                .path("data.getTripById")
                .entity(TripDto.class)
                .get();

        Assertions.assertEquals(tripDto, trip);
    }

    @Test
    void shouldCreateTrip() {
        TripDto tripDto = TripDto.builder().id(UUID.randomUUID()).build();
        Mockito.when(tripService.createTrip(Mockito.any())).thenReturn(tripDto);
        String trip = tester.document(
                """
                        mutation Create {
                            createTrip(trip: {
                                startDate: "2024-10-10"
                                endDate: "2024-11-11"
                                coordinates: [
                                    {
                                        latitude: 1.0
                                        longitude: 2.0
                                    },
                                    {
                                        latitude: 3.0
                                        longitude: 4.0
                                    }
                                ]
                                guests: ["one", "two"]
                            })
                        }
                        """)
                .execute()
                .path("data.createTrip")
                .entity(String.class)
                .get();

        Assertions.assertNotNull(trip);
    }

    @Test
    void shouldUpdateTrip() {
        TripDto updatedTrip = TripDto.builder().id(UUID.randomUUID()).build();

        Mockito.when(tripService.updateTrip(Mockito.any(), Mockito.any())).thenReturn(updatedTrip);
        String trip = tester.document(
                """
                        mutation Update {
                            updateTrip(id: "%s"
                                trip: {
                                    startDate: "2024-10-10"
                                    endDate: "2024-11-11"
                                    coordinates: [
                                        {
                                            latitude: 1.0
                                            longitude: 2.0
                                        },
                                        {
                                            latitude: 3.0
                                            longitude: 4.0
                                        }
                                    ]
                                    guests: ["one", "two"]
                            })
                        }
                        """.formatted(updatedTrip.id()))
                .execute()
                .path("data.updateTrip")
                .entity(String.class)
                .get();

        Assertions.assertNotNull(trip);
    }

    @Test
    void shouldDeleteTripById() {
        UUID id = UUID.randomUUID();
        Mockito.doNothing().when(tripService).deleteTripById(id);

        String result = tester.document(
                """
                        mutation {
                            deleteTripById(id: "%s")
                        }
                        """.formatted(id))
                .execute()
                .path("data.deleteTripById")
                .entity(String.class)
                .get();

        Assertions.assertEquals("Trip has been deleted successfully", result);
    }
}
