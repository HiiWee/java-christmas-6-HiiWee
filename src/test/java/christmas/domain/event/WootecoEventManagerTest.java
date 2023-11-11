package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

import christmas.domain.BookingRepository;
import christmas.domain.WootecoRestaurantManager;
import christmas.domain.date.SelectedDate;
import christmas.domain.event.history.EventParticipationHistory;
import christmas.domain.menu.Menu;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.reservation.Reservation;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WootecoEventManagerTest {

    @InjectMocks
    WootecoRestaurantManager restaurantManager;

    @Mock
    BookingRepository bookingRepository;

    WootecoEventManager eventManager;

    EventRepository eventRepository;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() throws Exception {
        autoCloseable = MockitoAnnotations.openMocks(this);
        eventRepository = new EventRepository();
        eventManager = new WootecoEventManager(eventRepository);
        autoCloseable.close();
    }


    @DisplayName("이벤트에 등록하면 이벤트에 대한 혜택 기록을 저장할 수 있다.")
    @Test
    void applyAllEvents() {
        // given
        int expectedBenefitPrice = 2023 * 10 + 3400 + 1000 + 25000;
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("타파스-10", "초코케이크-10"));
        SelectedDate selectedDate = SelectedDate.createFrom("25");
        Reservation reservation = new Reservation(selectedMenus, selectedDate);

        given(bookingRepository.findReservation()).willReturn(Optional.of(reservation));

        // when
        eventManager.applyAllEvents(restaurantManager);
        EventParticipationHistory history = eventRepository.findEventHistory().get();
        int benefitPrice = history.benefits()
                .events()
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        List<Menu> menus = history.gifts()
                .menus();

        // then
        assertAll(
                () -> assertThat(benefitPrice).isEqualTo(expectedBenefitPrice),
                () -> assertThat(menus).contains(Menu.CHAMPAGNE)
        );

    }

}