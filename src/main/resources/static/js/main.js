//좌우 버튼을 누르면 상품목록이 차례대로 바뀐다 다음에는 ajax로 상품사진클릭시 내용보여주는것도 가능할거같음.
$(document).ready(function() {
	$(".roll_left").click(function() {
		// jquery에 insertAfter 함수로 조건이 일치되는 요소 뒤에 해당 요소를 삽입을 합니다
		$(".roll li").eq(0).insertAfter(".roll li:last-child");

	});


	$(".roll_right").click(function() {
		// jquery에 insertBefore 함수로 조건이 일치되는 요소 뒤에 해당 요소를 삽입을 합니다
		$(".roll li").eq(-1).insertBefore(".roll li:first-child");

	});


});

const inputs = document.querySelectorAll(".input");


function addcl() {
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl() {
	let parent = this.parentNode.parentNode;
	if (this.value == "") {
		parent.classList.remove("focus");
	}
}


inputs.forEach(input => {
	input.addEventListener("focus", addcl);
	input.addEventListener("blur", remcl);
});

window.addEventListener('load', async function() {
	//장바구니 수량 계산해서 몇개있는지 표시해줌
	const CartRes = await fetch('/shopping-carts');

	const Cartobjs = await CartRes.text();

	if (Cartobjs != '') {
		Cartjson = JSON.parse(Cartobjs);
		let html = Cartjson.length;
		document.querySelector('#cart').innerText = html;
	}
	
	// 유저가 보유한 쿠폰 개수 계산해서 몇개있는지 표시
	const CouponRes = await fetch('/coupon-infos');
	const Couponobjs = await CouponRes.text();

	if (Couponobjs != '') {
		let Couponjson = JSON.parse(Couponobjs);
		console.log(Couponjson);
		let Couponhtml = Couponjson.length;
		document.querySelector('#coupon').innerText = Couponhtml;
	}


	//대충 헬퍼써서 전체 상품목록 불러옴
	const res = await fetch('/product-infos');
	const productInfo = await res.json();
	console.log(productInfo);
	const productFiles = [];
	let html = '';
	//랜덤으로 3개 상품 골라와서 추천상품에 보여줍니다
	for (let i = 0; i < 3; i++) {
		const product = productInfo[Math.floor(Math.random() * productInfo.length)];
		console.log(product);
		if (productFiles.includes(product)) {
			i--;
		} else {
			productFiles.push(product);
			//평점평균구해서 별개수 표시. 리뷰개수 표현하기
			const AvgRes = await fetch(`/comment-info/average?piNum=${product.piNum}`);
			const average = await AvgRes.text();
			const avg = Number(average);

			html += `<div class="col-12 col-md-4 mb-4">
							<div class="card h-100">
								<a href="/fbs/product/product-view?piNum=${product.piNum}" >
									<img src="${product.pfiImgPath}" class="card-img-top" alt="list${i}" onclick="location.href='/fbs/product/product-view?piNum=${product.piNum}'">
								</a>
								<div class="card-body">
									<ul class="list-unstyled d-flex justify-content-between">
										<li>`

			for (let is = 0; is < avg; is++) {
				html += `<i class="text-warning fa fa-star"></i>`
			}
			for (let j = 0; j < 5 - avg; j++) {
				html += `<i class="text-muted fa fa-star"></i>`
			}
			html += `
									</li>
										<li class="text-muted text-right">${product.piPrice} 원</li>
									</ul>
									<a href="/fbs/product/product-view?piNum=${product.piNum}" class="h3 text-decoration-none text-dark"><b>${product.piName}</b></a><br>
									<p class="card-text2">
										${product.piDesc}
									</p>
								</div>
							</div>
						</div>`

		}

	}
	document.querySelector('#recomendProduct').innerHTML = html;

	recent_html = ``;
	//최근 등록된 상품 3개
	for (let j = 0; j < 3; j++) {
		const product = productInfo[j];
		recent_html += `<div class="col-12 col-md-4 p-5 mt-3">
							<a href="/fbs/product/product-view?piNum=${product.piNum}" onclick="addRecentInfo(${product.piNum})"><img src="${product.pfiImgPath}" class="rounded-circle img-fluid border"></a>
							<h5 class="text-center mt-3 mb-3"><b>${product.piName}</b></h5>
							<p class="text-center"><a href="/fbs/product/product-view?piNum=${product.piNum}" class="btn btn-success">Go Shop</a></p>
						</div>`
	}
	document.querySelector('#products').innerHTML = recent_html; 	

});

