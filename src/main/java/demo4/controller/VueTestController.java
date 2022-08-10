package demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueTestController {

    // hello vue
    @RequestMapping("/vueJsTest")
    public String vue1() {

        return "thymeleaf/vue/helloVue.html";
    }

    // vue 바인드  v-vind
    @RequestMapping("/vueJsTest2")
    public String vue2() {

        return "thymeleaf/vue/helloVue2.html";
    }

    // vue 조건문 v-if
    @RequestMapping("/vueJsTest3")
    public String vue3() {
        return "thymeleaf/vue/helloVue3.html";
    }

    // vue 반복문 v-for
    @RequestMapping("/vueJsTest4")
    public String vue4() {

        return "thymeleaf/vue/helloVue4.html";
    }

    // vue 사용자 입력 핸들링 v-on onlcick
    @RequestMapping("/vueJsTest5")
    public String vue5() {

        return "thymeleaf/vue/helloVue5.html";
    }

     // vue 사용자 입력 핸들링 v-model
    @RequestMapping("/vueJsTest6")
    public String vue6() {

        return "thymeleaf/vue/helloVue6.html";
    }

     // vue 사용자 입력 핸들링 v-model
    @RequestMapping("/vueJsTest7")
    public String vue7() {

        return "thymeleaf/vue/helloVue7.html";
    }
    
    // vue  computed 속성
    @RequestMapping("/vueJsTest8")
    public String vue8() {

        return "thymeleaf/vue/helloVue8.html";
    }

    // vue  computed 와 methods
    @RequestMapping("/vueJsTest9")
    public String vue9() {
    // 결과는 같을 수 있으나
    // computed 속성은 종속 대상을 따라 저장(캐싱)된다는 것
    // methods 는 캐싱 개념이 없이 매번 재 랜더링이 됨

        return "thymeleaf/vue/helloVue9.html";
    }

      // vue  computed 와 watch
    @RequestMapping("/vueJsTest10")
    public String vue10() {
    // 결과는 같을 수 있으나
    // computed 속성은 종속 대상을 따라 저장(캐싱)된다는 것
    // watch는 데이터 변경 되었을 때 호출되는 콜백 함수
    // 보통은 선언형 프로그래밍인 computed를 사용하는 것이 좋음

        return "thymeleaf/vue/helloVue10.html";
    }



    @RequestMapping("/vueJsTest11")
    public String vue11() {
        // vue  사용자가 만든 감시자가 필요한 경우에는 watch가 더 좋을 수 있음
        // watch를 사용하면, API롤 호출하고 그 결과에 대한 응답을 받기 전까지 중간 상태를 설정할 수있습니다

        return "thymeleaf/vue/helloVue11.html";
    }

       @RequestMapping("/telTest")
    public String telTest() {
        // vue  사용자가 만든 감시자가 필요한 경우에는 watch가 더 좋을 수 있음
        // watch를 사용하면, API롤 호출하고 그 결과에 대한 응답을 받기 전까지 중간 상태를 설정할 수있습니다

        return "thymeleaf/vue/telTest.html";
    }

    @RequestMapping("/vueInJsp")
    public String vueInJsp() {
        // vue  사용자가 만든 감시자가 필요한 경우에는 watch가 더 좋을 수 있음
        // watch를 사용하면, API롤 호출하고 그 결과에 대한 응답을 받기 전까지 중간 상태를 설정할 수있습니다

        return "thymeleaf/vue/vueInJsp.html";
    }

}
