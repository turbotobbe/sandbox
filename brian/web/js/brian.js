var meta = {
    capKey: "",
    labelId: "",
    canvasId: "",
    datesId: "",
    namesId: ""
}
var stored = {
    nameKey: "",
    nameStr: "",
    dateKey: ""
}

/* setup */

function init(capKey, labelId, canvasId, datesId, namesId) {
    meta.capKey = capKey;
    meta.labelId = labelId;
    meta.canvasId = canvasId;
    meta.datesId = datesId;
    meta.namesId = namesId;
    s = sessionStorage[capKey + "-stored"];
    if (s == null) {
	//nowKey = makeDateKey(new Date());
	stored = {
	    nameKey: "",
	    nameStr: "",
	    dateKey: ""
	}
    } else {
	stored = JSON.parse(s);
    }
    //$("#log").html("stored:" + JSON.stringify(stored));
    loadDates();
    loadNames();
}

function updateStore() {
    sessionStorage[meta.capKey + "-stored"] = JSON.stringify(stored);
    //$("#log").html("stored:" + JSON.stringify(stored));
}

/* dates */

function loadDates() {
    console.log("Loading " + meta.capKey + " dates ... ");
    $.getJSON("data/" + meta.capKey + "/dates.json", function(data){
	if (stored.dateKey == "" && data.dates.length > 0)
	{
	    stored.dateKey = data.dates[0];
	}
	for (var i = 0; i < data.dates.length; i++){
	    var div = '<div>';
	    div += '<a id="' + data.dates[i]+ '" href="#">' + data.dates[i] + '</a>';
	    div += '</div>';
	    $('#' + meta.datesId).append(div);
	    $("a#" + data.dates[i]).click(function(event){
		event.preventDefault();
		stored.dateKey = this.id;
		updateStore();
		reload();
	    });
	}
	reload();
    })
	.error(function(data){console.log("Loading " + meta.capKey + " dates ... ERROR: " + JSON.stringify(data));});
}

/* names */

function loadNames() {
    console.log("Loading " + meta.capKey + " names ... ");
    $.getJSON("data/" + meta.capKey + "/names.json", function(data){
	if (stored.nameKey == "" && data.names.length > 0)
	{
	    stored.nameKey = data.names[0].key;
	    stored.nameStr = data.names[0].name;
	}
	for (var i = 0; i < data.names.length; i++){
	    var key = data.names[i].key;
	    var div = '';
	    div += '<a id="' + key + '" href="#">' + data.names[i].name + '</a>';
	    div += ' | ';
	    $('#' + meta.namesId).append(div);
	    $("a#" + key).click(function(event){
		event.preventDefault();
		stored.nameKey = this.id;
		stored.nameStr = this.innerHTML;
		updateStore();
		reload();
	    });
	}
	reload();
    })
	.error(function(data){console.log("Loading " + meta.capKey + " names ... ERROR: " + JSON.stringify(data));});
}

/* util */

function makeDateKey(date) {
    var str = date.getFullYear();
    str = (str + "").substring(0, 4);
    str += "-";
    if (date.getMonth() < 9) {
	str += "0";
    }
    str += (date.getMonth()+1);
    str += "-";
    if (date.getDate() < 10) {
	str += "0";
    }
    str += date.getDate();
    return str;
}

function makeDate(date, time) {
    var year = date.substring(0, 4);
    var month = date.substring(5, 7);
    var day = date.substring(8, 10);
    var hour = time.substring(0, 2);
    hour++;
    var minute = time.substring(3, 5);
    var date = new Date(year,month,day,hour,minute,0,0);
    return date;
}

/* graph */

function reload() {
    
    $("a").removeClass("selected");
    $("a#" + meta.capKey).addClass("selected");
    if (stored.dateKey != "") {
	$("a#" + stored.dateKey).addClass("selected");
    }
    if (stored.nameKey != "") {
	$("a#" + stored.nameKey).addClass("selected");
    }
    plot(null);
    if (stored.dateKey == "" || stored.nameKey == "") {
	return;
    }
    var quotes = [];
    console.log("Loading " + meta.capKey + " quotes ...");
    $.getJSON("data/" + meta.capKey + '/quotes/' + stored.dateKey + '/' + stored.nameKey + '.json', function(data){
	var volume = [];
	var buy = [];
	var sell = [];
	for (var i = 0; i < data.quotes.length; i++) {
	    var date = makeDate(data.date, data.quotes[i].atime);
	    console.log(date.getTime());
	    volume.push([date.getTime(), (data.quotes[i].volume * 1)]);
	    buy.push([date.getTime(), (data.quotes[i].buy * 1)]);
	    sell.push([date.getTime(), (data.quotes[i].sell * 1)]);
	}
	quotes['volume'] = volume;
	quotes['buy'] = buy;
	quotes['sell'] = sell;
	console.log(quotes);
	plot(quotes);
    })
	.error(function(data){console.log("error: " + JSON.stringify(data));});
}

function plot(quotes) {
    if (quotes == null) {
	$("#" + meta.labelId).html("");
	$.plot($("#" + meta.canvasId), []);
	return;
    }
    console.log("plotting ...");
    $("#" + meta.labelId).html(stored.nameStr + " (" + stored.dateKey + ")");
    var data = [];
    data.push({ label: "Sell", color: "green", data: quotes['sell'] });
    data.push({ label: "Buy", color: "blue", data: quotes['buy'] });
    $.plot($("#" + meta.canvasId), data, {
	series: {
	    lines: { show: true },
	    points: { show: false }
	},
	xaxis: { mode: "time" }
    });
}
