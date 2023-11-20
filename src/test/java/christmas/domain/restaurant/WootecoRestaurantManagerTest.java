package christmas.domain.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import christmas.dto.reservation.ReservedResults;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WootecoRestaurantManagerTest {

    BookingRepository repository;
    WootecoRestaurantManager manager;

    @BeforeEach
    void setUp() {
        repository = new BookingRepository();
        manager = new WootecoRestaurantManager(repository);
    }

    @DisplayName("선택한 날짜를 저장할 수 있다.")
    @Test
    void addSelectedDate() {
        // given
        int inputDate = 10;
        SelectedDate expectedDate = SelectedDate.createFrom(inputDate);

        // when
        manager.addSelectedDate(inputDate);
        SelectedDate actualDate = repository.findSelectedDate().get();

        // then
        assertThat(actualDate).isEqualTo(expectedDate);
    }

    @DisplayName("선택한 메뉴를 저장할 수 있다.")
    @Test
    void addSelectedMenus() {
        // given
        List<String> inputMenus = List.of("해산물파스타-2", "타파스-10");
        SelectedMenus expectedMenus = SelectedMenus.createFrom(inputMenus);

        // when
        manager.addSelectedMenus(inputMenus);
        SelectedMenus actualMenus = repository.findSelectedMenus().get();

        // then
        assertThat(actualMenus).isEqualTo(expectedMenus);
    }

    @DisplayName("예약한 결과를 받아올 수 있다.")
    @Test
    void createReservationResults() {
        // given
        String expectedMenuNameMessage = """
                해산물파스타 2개
                타파스 10개""";
        String expectedTotalPriceMessage = "125,000원";
        manager.addSelectedDate(10);
        manager.addSelectedMenus(List.of("해산물파스타-2", "타파스-10"));
        manager.makeReservation();

        // when
        ReservedResults reservedResults = manager.createReservationResults();
        String menuNameMessage = reservedResults.createMenuNameMessage();
        String totalPriceMessage = reservedResults.createTotalPriceMessage();

        // then
        assertAll(
                () -> assertThat(menuNameMessage).isEqualTo(expectedMenuNameMessage),
                () -> assertThat(totalPriceMessage).isEqualTo(expectedTotalPriceMessage)
        );
    }

    @DisplayName("예약한 결과가 없다면 예외가 발생한다.")
    @Test
    void createReservationResults_exception_notFoundReservation() {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> manager.createReservationResults());
    }

    @DisplayName("선택한 메뉴와 날짜가 있다면 예약 정보가 업데이트 된다.")
    @Test
    void updateReservation() {
        // given
        manager.addSelectedDate(10);
        manager.addSelectedMenus(List.of("해산물파스타-2", "타파스-10"));

        // when
        Optional<Reservation> beforeUpdate = repository.findReservation();
        manager.makeReservation();
        Optional<Reservation> afterUpdate = repository.findReservation();

        // then
        assertAll(
                () -> assertThat(beforeUpdate.isEmpty()).isTrue(),
                () -> assertThat(afterUpdate.isPresent()).isTrue()
        );
    }
}
