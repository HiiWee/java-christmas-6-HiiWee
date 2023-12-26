package christmas.domain.restaurant.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("없는 이름의 메뉴를 찾으면 예외가 발생한다.")
    @Test
    void find_exception_notFoundName() {
        // given
        String invalidName = "없는이름";

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> Menu.find(invalidName));

    }

    @DisplayName("이름으로 메뉴를 찾을 수 있다.")
    @Test
    void find() {
        // given
        List<String> menuNames = List.of("티본스테이크", "해산물파스타", "초코케이크");

        // when
        List<Menu> menus = menuNames.stream()
                .map(Menu::find)
                .toList();

        // then
        assertThat(menus).contains(Menu.T_BONE_STEAK, Menu.SEAFOOD_PASTA, Menu.CHOCO_CAKE);
    }
}
