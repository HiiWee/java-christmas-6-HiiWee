package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

import christmas.domain.event.eventhistory.EventParticipationHistory;
import christmas.domain.restaurant.BookingRepository;
import christmas.domain.restaurant.WootecoRestaurantManager;
import christmas.domain.restaurant.date.SelectedDate;
import christmas.domain.restaurant.menu.Menu;
import christmas.domain.restaurant.menu.SelectedMenus;
import christmas.domain.restaurant.reservation.Reservation;
import christmas.dto.BadgeResult;
import christmas.dto.BenefitDetails;
import christmas.dto.PaymentAmountResult;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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


    @DisplayName("이벤트에 등록하면 이벤트에 대한 혜택 기록을 저장하고 뱃지를 받을 수 있다.")
    @Test
    void applyAllEvents() {
        // given
        int expectedBenefitPrice = 2023 * 10 + 3400 + 1000 + 25000;
        SelectedMenus selectedMenus = SelectedMenus.createFrom(List.of("타파스-10", "초코케이크-10"));
        SelectedDate selectedDate = SelectedDate.createFrom("25");
        Reservation reservation = new Reservation(selectedMenus, selectedDate);

        given(bookingRepository.findReservation()).willReturn(Optional.of(reservation));

        // when
        eventManager.applyAllEvents(restaurantManager::findReservationObject);
        EventParticipationHistory history = eventRepository.findEventHistory().get();
        int benefitPrice = history.calculateTotalBenefit();
        EventBadge eventBadge = EventBadge.findBadge(benefitPrice);
        Map<Menu, Integer> giftCounts = history.giftCounts().giftCounts();

        // then
        assertAll(
                () -> assertThat(eventBadge).isEqualTo(EventBadge.SANTA),
                () -> assertThat(benefitPrice).isEqualTo(expectedBenefitPrice),
                () -> assertThat(giftCounts.get(Menu.CHAMPAGNE)).isEqualTo(1)
        );
    }

    @DisplayName("이벤트에 대한 혜택 내역을 받아볼 수 있다.")
    @Test
    void createBenefitDetails() {
        // given
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        history.participateEvent(EventType.WEEKEND_EVENT, 555555);
        eventRepository.saveEventHistory(history);

        // when
        BenefitDetails benefitDetails = eventManager.createBenefitDetails();
        int actualBenefit = benefitDetails.benefitPrices().benefitPrices().get(EventType.WEEKEND_EVENT.getEventName());

        // then
        assertThat(actualBenefit).isEqualTo(555555);
    }

    @DisplayName("혜택을 적용한 최종 결제 금액을 알 수 있다.")
    @Test
    void createPaymentAmount() {
        // given
        int eventBenefit = 50_000;
        int menuPrice = 145_000;
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        history.participateEvent(EventType.WEEKEND_EVENT, eventBenefit);
        Reservation reservation = new Reservation(
                SelectedMenus.createFrom(List.of("해산물파스타-2", "레드와인-1", "초코케이크-1")),
                SelectedDate.createFrom("24")
        );
        given(bookingRepository.findReservation()).willReturn(Optional.of(reservation));

        // when
        eventRepository.saveEventHistory(history);
        PaymentAmountResult paymentAmount = eventManager.createPaymentAmount(restaurantManager::findReservationObject);

        // then
        assertThat(paymentAmount.paymentAmount()).isEqualTo(menuPrice - eventBenefit);
    }

    @DisplayName("혜택 금액에 따른 이벤트 배지를 받을 수 있다.")
    @Test
    void selectEventBadge() {
        // given
        EventParticipationHistory history = EventParticipationHistory.getInstance();
        history.participateEvent(EventType.WEEKEND_EVENT, 20_000);

        // when
        eventRepository.saveEventHistory(history);
        BadgeResult badgeResult = eventManager.selectEventBadge();

        // then
        assertThat(badgeResult.name()).isEqualTo(EventBadge.SANTA.getName());
    }
}