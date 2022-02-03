package potvizsga.streams;

import java.util.ArrayList;
import java.util.List;

public class ApartmentRental {

    private List<Apartment> apartments = new ArrayList<>();


    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }


    public List<Apartment> findApartmentByLocation(String city) {
        return apartments.stream()
                .filter(apartment -> apartment.getLocation().equals(city))
                .toList();
    }


    public List<Apartment> findApartmentByExtras(String... extras) {
        return apartments.stream()
//                .filter(apartment -> Arrays.stream(extras)
//                        .allMatch(extra -> apartment.getExtras().stream().anyMatch(extra::equals)))
//                .filter(apartment -> Arrays.stream(extras)
//                        .allMatch(extra -> apartment.getExtras().contains(extra)))
                .filter(apartment -> apartment.getExtras().containsAll(List.of(extras)))
                .toList();
    }


    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        return apartments.stream()
                .anyMatch(apartment -> apartment.getBathRoomType() == bathRoomType);
    }

    public List<Integer> findApartmentsSize() {
        return apartments.stream()
                .map(Apartment::getSize)
                .toList();
    }
}
