// 선택자 
let scanFileContainer = document.querySelector('#scanFileContainer');

// connect
var stompClient = null;	
connect();
function connect() { 
	if (stompClient !== null && stompClient.connected) {
        stompClient.disconnect();
    }
	
    var socket = new SockJS('/ws'); 
    stompClient = Stomp.over(socket);									  
	
		 
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/rendering', function (returnMap) { 
			addImg(JSON.parse(returnMap.body));
        });
    });    
}

// 이미지 자동 추가 
function addImg(returnMap){
	let Imgsrc = returnMap.flag;
	let fileNo = returnMap.fileNo;
	
	let newLi = document.createElement('li');
	newLi.style.listStyleType = 'none';
	
	let newA = document.createElement('a');
	newA.href = '/file/' + fileNo;
	
	let newImg = document.createElement('img');
	newImg.src = Imgsrc;
	newImg.classList.add('scanedImg');
	
	
	newLi.appendChild(newA);
	newA.appendChild(newImg);
	
	scanFileContainer.appendChild(newLi);
	
}
