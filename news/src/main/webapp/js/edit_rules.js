document.getElementById('newsForm').setAttribute('id', 'newsForm');
//document.getElementById('date').setAttribute('type', 'date');
//document.getElementById('brief').setAttribute('maxlength', '500');
//document.getElementById('brief').setAttribute('required', 'true');
//document.getElementById('title').setAttribute('required', 'true');
//document.getElementById('content').setAttribute('required', 'true');
//document.getElementById('date').setAttribute('required', 'true');
//document.getElementById('content').setAttribute('maxlength', '2048');

function convertDate() {
	var elem = document.getElementById("date");
	var date = new Date(elem.value);
	var date_str = moment(date).format("YYYY-MM-DD");
	elem.value = date_str;

}
