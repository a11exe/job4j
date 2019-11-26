<%@ page contentType="text/html;charset=UTF-8" %>

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <title>Movie tickets online booking</title>
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
<script src="js/cinema.js"></script>

<div class="container">
  <div class="row pt-3">
    <div>
    <h4>Tickets online booking</h4>
    </div>
    <br>
    <div class="container">
    <div id="selected" class="alert alert-light" role="alert">Book a seat</div>
      <div class="text-danger">
        <div id="bookTime"></div>
      </div>
    </div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 120px;">Row / Seat Number</th>
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
            aria-pressed="true" data-toggle="modal" data-target="#booking" onclick="addBookingInfo()">Booking</button>
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
            <h5 id="selectedSeat"></h5>
          </div>
          <div class="form-group">
            <input type="hidden" name="id" class="form-control" id="id" value="">
          </div>
          <div class="form-group">
            <label for="username">Name</label>
            <input name="username" type="text" class="form-control" id="username" placeholder="ФИО">
          </div>
          <div class="form-group">
            <label for="phone">Phone</label>
            <input name="phone" type="text" class="form-control" id="phone" placeholder="Номер телефона">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="confirmBooking()">Confirm booking</button>
      </div>
    </div>
  </div>
</div>

</body>

</html>