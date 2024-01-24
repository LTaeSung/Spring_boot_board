/*
	replyManager 변수에 들어있는것
	{
		getAll:getAll함수, 
		add:add함수, 
		update:update함수, 
		remove:remove함수
	}
*/
var replyManager = (function() {
	var getAll = function(obj, callback) {
		console.log("get all...");
		$.getJSON('/replies/'+obj, callback);

	};
	var add = function(obj, callback) {
		console.log("add...");
		$.ajax({
			type:'post',
			url:'/replies/'+obj.bno,
			data:JSON.stringify(obj),
			dataType: 'json',
			contentType: 'application/json',
			success:callback
		});
	};
	var update = function(obj, callback) {
		console.log("update...");
		$.ajax({
			type:'put',
			url:'/replies/'+obj.bno,
			data:JSON.stringify(obj),
			dataType: 'json',
			contentType: 'application/json',
			success:callback
		});
	};
	var remove = function(obj, callback) {
		console.log("remove...");
		$.ajax({
			type:'delete',
			url:'/replies/'+obj.bno+"/"+obj.rno,
			data:JSON.stringify(obj),
			dataType: 'json',
			contentType: 'application/json',
			success:callback
		});
	};
	return {
		getAll:getAll,
		add:add,
		update:update,
		remove:remove
	}
})();