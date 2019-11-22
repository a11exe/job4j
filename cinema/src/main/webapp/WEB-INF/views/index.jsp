<%@ page contentType="text/html;charset=UTF-8" %>

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <title>Бронирование мест на сеанс</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="/js/jquery.cookie.js"></script>

<div class="container">
  <div class="row pt-3">
    <div>
    <h4>Бронирование мест на сеанс</h4>
    </div>
    <br>
    <div class="container">
    <div id="selected" class="alert alert-light" role="alert">Выберите место</div>
      <div class="text-danger">
        <div id="bookTime"></div>
      </div>
    </div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 120px;">Ряд / Место</th>
        <th>1</th>
        <th>2</th>
        <th>3</th>
      </tr>
      </thead>
      <tbody id="tbody">
      </tbody>
    </table>
  </div>
  <div class="row float-right">
    <button type="button" class="btn btn-success"
            aria-pressed="true" data-toggle="modal" data-target="#booking" onclick="pay()">Оплатить</button>
  </div>
</div>

<div class="modal fade" id="booking" tabindex="-1" role="dialog" aria-labelledby="booking" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="seat">
          <div class="form-group">
            <h5 id="selectedSeat">Вы выбрали ряд 1 место 1, Сумма : 500 рублей. </h5>
          </div>
          <div class="form-group">
            <input type="hidden" name="id" class="form-control" id="id" value="">
          </div>
          <div class="form-group">
            <label for="username">ФИО</label>
            <input type="text" class="form-control" id="username" placeholder="ФИО">
          </div>
          <div class="form-group">
            <label for="phone">Номер телефона</label>
            <input type="text" class="form-control" id="phone" placeholder="Номер телефона">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="pay()">Оплатить</button>
      </div>
    </div>
  </div>
</div>

</body>
<script>
  $(document).ready(function () {
    loadHall();
  });
</script>
<script>

  var seats;
  var selectedSeatIndex = "";

  setInterval(function() {
    loadHall();
  } ,10000);

  function getCellSeat(i) {
      var seatNum = seats[i].number;
      var row = seats[i].row;
      var seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-secondary\"><input type=\"radio\" value=\"" + seats[i].id + "\" disabled> Ряд "
          + row + ", Место " + seatNum + "</td>";
      debugger
      if (seats[i].state == "BOOKED") {
          seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-danger\"><input type=\"radio\" value=\"" + seats[i].id + "\" disabled> Ряд "
              + row + ", Место " + seatNum + "</td>";
      }
      if (seats[i].state == "PENDING") {
          seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-danger\"><input type=\"radio\" value=\"" + seats[i].id + "\" disabled> Ряд "
              + row + ", Место " + seatNum + "</td>";
      }
      if (seats[i].state == "FREE") {
          seatStr = "<td id =\"seat" + i + "\" class=\"alert alert-light\"><input type=\"radio\" name=\"place\" value=\"" + seats[i].id + "\" onclick='book(" + i + ")'> Ряд "
              + row + ", Место " + seatNum + "</td>";
      }
      return seatStr;
  }


  function loadHall() {
    $.ajax({
      url: "/hall",
      type: 'GET',
      data: {
        sessionID: sessionID,
      },
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

      },
      error: function (jqXHR, textStatus, errorThrown) {
        console.log("Error... " + textStatus + "        " + errorThrown);
      },
    });
  }

  function book(id) {
    $(document.getElementById("selected")).removeClass("alert-light").addClass("alert-primary");
    if (selectedSeatIndex !== "") {
      var cellPrev = document.getElementById("seat" + selectedSeatIndex);
      $(cellPrev).removeClass("alert-success").addClass("alert-light");
    }
    selectedSeatIndex = id;
    selectedSeat = seats[id];
    var selectedSeatEl = document.getElementById("selected");
    var cell = document.getElementById("seat" + selectedSeatIndex);
    $(cell).removeClass("alert-light").addClass("alert-success");
    selectedSeatEl.innerHTML = "Выбрано. Ряд: " + selectedSeat.row + " место: " + selectedSeat.number;
    seconds = 300;
    countdownTimer = setInterval('secondPassed()', 1000);
    var seat = seats[selectedSeatIndex];
    seat.sessionID = sessionID;
    $.ajax({
      url: "/book",
      type: 'POST',
      cache: false,
      datatype: 'json',
      contentType: 'application/json;charset=UTF-8',
      data: JSON.stringify(seats[selectedSeatIndex]),
      success: function (data) {

      }
    })
  }

  var seconds = 300; //**change 120 for any number you want, it's the seconds **//
  function secondPassed() {
    var minutes = Math.round((seconds - 30)/60);
    var remainingSeconds = seconds % 60;
    if (remainingSeconds < 10) {
      remainingSeconds = "0" + remainingSeconds;
    }
    document.getElementById('bookTime').innerHTML = "Бронь будет снята через <span id=\"countdown\" class=\"timer\"></span>";
    document.getElementById('countdown').innerHTML = minutes + ":" + remainingSeconds;
    if (seconds == 0) {
      clearInterval(countdownTimer);
      document.getElementById('countdown').innerHTML = "Бронь снята";
    } else {
      seconds--;
    }

  }

  // var countdownTimer = setInterval('secondPassed()', 1000);

  var sessionID = $.cookie("sessionId");

</script>
</html>