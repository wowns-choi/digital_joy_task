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

function addImg(returnMap) {
    let Imgsrc = returnMap.flag;
    let fileNo = returnMap.fileNo;
    let extension = returnMap.extension;
    
    let newLi = document.createElement('li');
    newLi.style.listStyleType = 'none';
    
    let newA = document.createElement('a');
    newA.href = '/file/' + fileNo;
    
    if (extension === '.jpg' || extension === '.jpeg') {
        let newImg = document.createElement('img');
        newImg.src = Imgsrc;
        newImg.classList.add('scanedImg');
        newA.appendChild(newImg);
    } else if (extension === '.pdf') {
        let newObject = document.createElement('object');
        newObject.data = Imgsrc;
        newObject.type = 'application/pdf';
        newObject.width = '100%';
        newObject.height = '600px';
        newObject.classList.add('scanedImg');
        
        newA.appendChild(newObject);
    } else {
        // JPG도 PDF도 아닌 경우 기본적으로 <img> 태그로 처리
        let newImg = document.createElement('img');
        newImg.src = Imgsrc;
        newImg.classList.add('scanedImg');
        newA.appendChild(newImg);
    }
    
    
    newLi.appendChild(newA);
    scanFileContainer.appendChild(newLi);
}
