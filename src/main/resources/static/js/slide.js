const slide = document.querySelector('.slide');
const itemList = document.querySelector('.slide_wrap');
const items = document.querySelectorAll('.slide_item');
let currentIndex = 0; // 현재 슬라이드 화면 인덱스

items.forEach((item) => {
  item.style.width = `${slide.clientWidth}px`; // item의 width를 모두 slide의 width로 만들기
})

itemList.style.width = `${slide.clientWidth * items.length}px`; // itemList의 width를 item의 width * items의 개수로 만들기

/*
  버튼에 이벤트 등록하기
*/
const buttonLeft = document.querySelector('.left');
const buttonRight = document.querySelector('.right');
/*
buttonLeft.addEventListener('click', () => {
  currentIndex--;
  currentIndex = currentIndex < 0 ? 0 : currentIndex; // index값이 0보다 작아질 경우 0으로 변경
  itemList.style.marginLeft = `-${slide.clientWidth * currentIndex}px`; // index만큼 margin을 주어 옆으로 밀기
  clearInterval(interval); // 기존 동작되던 interval 제거
  interval = getInterval(); // 새로운 interval 등록
});

buttonRight.addEventListener('click', () => {
  currentIndex++;
  currentIndex = currentIndex >= items.length ? items.length - 1 : currentIndex; // index값이 items의 총 개수보다 많아질 경우 마지막 인덱스값으로 변경
  itemList.style.marginLeft = `-${slide.clientWidth * currentIndex}px`; // index만큼 margin을 주어 옆으로 밀기
  clearInterval(interval); // 기존 동작되던 interval 제거
  interval = getInterval(); // 새로운 interval 등록
});
*/
/*
  주기적으로 화면 넘기기
*/
const getInterval = () => {
  return setInterval(() => {
    currentIndex++;
    currentIndex = currentIndex >= items.length ? 0 : currentIndex;
    itemList.style.marginLeft = `-${slide.clientWidth * currentIndex}px`;
  }, 5000);     /*일정 시간이 지나면 다음 이미지로 넘어가게 설정 */
}

let interval = getInterval(); 