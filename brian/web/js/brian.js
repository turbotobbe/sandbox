var meta = {
    capKey: "",
    labelId: "",
    quotesId: "",
    volumeId: "",
    datesId: "",
    namesId: ""
}
var stored = {
    nameKey: "",
    nameStr: "",
    dateKey: ""
}

/* setup */

function init(capKey, labelId, quotesId, volumeId, datesId, namesId) {
    meta.capKey = capKey;
    meta.labelId = labelId;
    meta.quotesId = quotesId;
    meta.volumeId = volumeId;
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
    var values = [];
    console.log("Loading " + meta.capKey + " quotes ...");
    $.getJSON("data/" + meta.capKey + '/quotes/' + stored.dateKey + '/' + stored.nameKey + '.json', function(data){
	var latest = [];
	var volume = [];
	var beg = makeDate(data.date, "09:15").getTime();
	var end = makeDate(data.date, "17:45").getTime();
	var off = (15*60*1000)
	for (var i = 0; i < data.quotes.length; i++) {
	    var date = makeDate(data.date, data.quotes[i].btime);
	    if (date > beg && date <= end) {
		latest.push([date.getTime(), (data.quotes[i].latest * 1)]);
		volume.push([date.getTime(), (data.quotes[i].volume * 1)]);
	    }
	}
	values['latest'] = latest;
	values['volume'] = volume;
	plot(values);
    })
	.error(function(data){console.log("error: " + JSON.stringify(data));});
}

function plot(values) {
    if (values == null) {
	$("#" + meta.labelId).html("");
	$.plot($("#" + meta.quotesId), []);
	$.plot($("#" + meta.volumeId), []);
	return;
    }
    console.log("plotting ...");
    $("#" + meta.labelId).html(stored.nameStr + " (" + stored.dateKey + ")");
    var latestData = [];
    latestData.push({ label: "Latest", color: "blue", data: values['latest'] });
    $.plot($("#" + meta.quotesId), latestData, {
	series: {
	    lines: { show: true },
	    points: { show: false }
	},
	xaxis: { mode: "time" }
    });
    var volumeData = [];
    volumeData.push({ label: "Volume", color: "blue", data: values['volume'] });
    $.plot($("#" + meta.volumeId), volumeData, {
    	series: {
    	    lines: { show: true },
    	    points: { show: false }
	},
	xaxis: { mode: "time" }
    });
}
