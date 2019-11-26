
$(document).ready(function () {
  loadHall();
});


var seats;
var selectedSeatIndex = "";

setInterval(function() {
  loadHall();
} ,10000);

function getCellSeat(i) {
  var seatNum = seats[i].number;
  var row = seats[i].row;
  var seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-secondary\"><input id =\"input" + i + "\" type=\"radio\" value=\"" + seats[i].id + "\" disabled> Ряд "
      + row + ", Место " + seatNum + "</td>";
  if (seats[i].state == "BOOKED") {
    seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-danger\"><input id =\"input" + i + "\" type=\"radio\" value=\"" + seats[i].id + "\" disabled> Ряд "
        + row + ", Место " + seatNum + "</td>";
  }
  if (seats[i].state == "PENDING") {
    selectedSeatIndex = i;
    seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-warning\"><input id =\"input" + i + "\" type=\"radio\" value=\"" + seats[i].id + "\" disabled> Ряд "
        + row + ", Место " + seatNum + "</td>";
  }
  if (seats[i].state == "FREE") {
    seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-light\"><input id =\"input" + i + "\" type=\"radio\" name=\"place\" value=\"" + seats[i].id + "\" onclick='startBooking(" + i + ")'> Ряд "
        + row + ", Место " + seatNum + "</td>";
  }
  return seatStr;
}


function loadHall() {
  $.ajax({
    url: "/hall",
    type: 'GET',
    cache: false,
    success: function (data) {
      var result = "";
      seats = data.hall;
      var row = "";
      for (var i = 0; i != seats.length; ++i) {
        if (i == 0) {
          row = seats[i].row;
          result += "<tr>"
              + "        <th>" + row + "</th>"
        } else if (row != seats[i].row) {
          row = seats[i].row;
          result += "</tr>\n"
              + "      <tr>\n"
              + "        <th>" + row + "</th>"
        }
        result += getCellSeat(i);
      }
      result += "</tr>"
      var tableBody = document.getElementById("tbody");
      tableBody.innerHTML = result;
      markChecked();
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log("Error... " + textStatus + "        " + errorThrown);
    },
  });
}

function markChecked() {
  debugger
  if (selectedSeatIndex !== "") {
    selectedSeat = seats[selectedSeatIndex];
    if (selectedSeat.state = "PENDING") {
      var selectedSeatEl = document.getElementById("selected");
      var cell = document.getElementById("seat" + selectedSeatIndex);
      $(cell).removeClass("alert-light").removeClass("alert-warning").addClass("alert-success");
      var input = document.getElementById("input" + selectedSeatIndex);
      $(input).prop("checked", true);
      selectedSeatEl.innerHTML = "Выбрано. Ряд: " + selectedSeat.row + " место: " + selectedSeat.number;
    }
  }
}

function startBooking(id) {
  $(document.getElementById("selected")).removeClass("alert-light").addClass("alert-primary");
  if (selectedSeatIndex !== "") {
    var cellPrev = document.getElementById("seat" + selectedSeatIndex);
    $(cellPrev).removeClass("alert-success").addClass("alert-light");
  }
  selectedSeatIndex = id;
  seconds = 300;
  countdownTimer = setInterval('secondPassed()', 1000);
  var seat = seats[selectedSeatIndex];
  $.ajax({
    url: "/book",
    type: 'POST',
    cache: false,
    datatype: 'json',
    contentType: 'application/json;charset=UTF-8',
    data: JSON.stringify(seats[selectedSeatIndex]),
    success: function (data) {
      markChecked();
      loadHall();
    },
    error: function (jqXHR, textStatus, errorThrown) {
      selectedSeatIndex = "";
      console.log("Error... " + textStatus + "        " + errorThrown);
      loadHall();
    },
  })
}

function addBookingInfo() {
  var selectedSeat = document.getElementById("selectedSeat");

  selectedSeat.innerHTML =
      "Вы выбрали ряд " + seats[selectedSeatIndex].row +
      " место " + seats[selectedSeatIndex].number +
      ", Сумма : " + seats[selectedSeatIndex].price + " рублей.";
}

function confirmBooking() {
  var seat = seats[selectedSeatIndex];
  var account = new Object();
  account.name = $('#username').val();
  account.phone = $('#phone').val();
  seat.account = account;

  $.ajax({
    url: "/confirm",
    type: 'POST',
    cache: false,
    datatype: 'json',
    contentType: 'application/json;charset=UTF-8',
    data: JSON.stringify(seat),
    success: function (data) {
    },
    error: function (jqXHR, textStatus, errorThrown) {
      selectedSeatIndex = "";
      console.log("Error... " + textStatus + "        " + errorThrown);
    },
  })
  loadHall();
}

var seconds = 300; //**change 120 for any number you want, it's the seconds **//
function secondPassed() {
  var minutes = Math.round((seconds - 30)/60);
  var remainingSeconds = seconds % 60;
  if (remainingSeconds < 10) {
    remainingSeconds = "0" + remainingSeconds;
  }
  document.getElementById('bookTime').innerHTML = "Booking will be canceled after <span id=\"countdown\" class=\"timer\"></span>";
  document.getElementById('countdown').innerHTML = minutes + ":" + remainingSeconds;
  if (seconds == 0) {
    clearInterval(countdownTimer);
    document.getElementById('countdown').innerHTML = "Booking canceled";
  } else {
    seconds--;
  }

}

var sessionId = $.cookie("sessionId");