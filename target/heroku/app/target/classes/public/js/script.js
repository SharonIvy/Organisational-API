$(Document).ready(function () {
  $('.max').toggle();
//	$('.minimise').toggle();
	$('.admin-toggle').slideToggle(1);

$('.tg-admin').click(function () {
   $('.admin-toggle').fadeToggle(1000);
  });

  $('.minimise').click(function () {
    $('.sidenav').css("left","-150px");
    $('.sidenav a').css("display", "none");
    $('.minimise').toggle();

    $('.max').slideToggle(1);
  });

  $('.max').click(function () {
    $('.sidenav').css("left","0px");
    $('.sidenav a').css("display", "block");
    $('.minimise').toggle();
    $('.max').slideToggle(1);
  });



});