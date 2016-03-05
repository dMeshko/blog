$(window).load(function() {
  $("body").removeClass("preload");
});


$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});


$(function() {
	
	// Cache the window object
	var $window = $(window);
	
	// Parallax background effect
	$('section[data-type="background"]').each(function() {
		
		var $bgobj = $(this); //assigning the object
		
		$(window).scroll(function() {
			
			// scroll the background at var speed
			// the yPos is a negative value because we're scrolling up!

			var yPos = -($window.scrollTop() / $bgobj.data('speed'));
			
			// Put together our final background position
			var coords = '50% '+ yPos + 'px';
			
			// Moive the background
			$bgobj.css({backgroundPosition: coords });
			
		}); // end window scroll
	});
	
});

$('.js--wp-1').waypoint(function(direction) {
					$('.js--wp-1').addClass('animated fadeInRight');	
						}, {
	offset: '50%'
})

$('.js--wp-2').waypoint(function(direction) {
					$('.js--wp-2').addClass('animated fadeInRight');	
						}, {
	offset: '50%'
})

$('.js--wp-11').waypoint(function(direction) {
					$('.js--wp-11').addClass('animated fadeIn');	
						}, {
	offset: '99%'
})

$('.js--wp-12').waypoint(function(direction) {
					$('.js--wp-12').addClass('animated bounceIn');	
						}, {
	offset: '99%'
})

$('.js--wp-13').waypoint(function(direction) {
					$('.js--wp-13').addClass('animated bounceIn');	
						}, {
	offset: '99%'
})

$('.js--wp-14').waypoint(function(direction) {
					$('.js--wp-14').addClass('animated bounceIn');	
						}, {
	offset: '99%'
})

$('.js--wp-15').waypoint(function(direction) {
					$('.js--wp-15').addClass('animated bounceIn');	
						}, {
	offset: '99%'
})

$('.js--wp-16').waypoint(function(direction) {
					$('.js--wp-16').addClass('animated bounceIn');	
						}, {
	offset: '99%'
})

$('.js--wp-17').waypoint(function(direction) {
					$('.js--wp-17').addClass('animated bounceIn');	
						}, {
	offset: '99%'
})