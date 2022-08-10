import oneComponent from './components/oneComponent.js'; // 컴포넌트1 import 
import twoComponent from './components/twoComponent.js'; // 컴포넌트2 import
import threeComponent from './components/threeComponent.js'; // 컴포넌트3 import

//각 컴포넌트들을 변수에 할당
const one_component = oneComponent;
const two_component = twoComponent;
const three_component = threeComponent;

//뷰인스턴스 생성 및 컴포넌트들 저장
new Vue({
	el : '#app',
	components: {
		'one-component': one_component,
		'two-component': two_component,
		'three-component': three_component
	}
})