/**
 * 
 */
// 해당 상품의 상세정보 불러오기
		window.addEventListener('load', async function () {

			const res = await fetch('/product-infos/[[${param.piNum}]]');
			const productInfo = await res.json();
			console.log(productInfo);
			prodInfo = productInfo;
			document.querySelector('#piNum').value = productInfo.piNum; // 상품 번호
			document.querySelector('#piNames').value = productInfo.piName; // 상품 이름
			document.querySelector('#piDesc').innerHTML = productInfo.piDesc; // 상품 설명
			document.querySelector('#piPrice').value = productInfo.piPrice; // 상품 가격
			}
			
)