<%-- 
    Document   : index
    Created on : Jul 30, 2022, 11:20:01 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="hero" id="hero">
    <div class="container">
        <div class="row">

            <div class="col-12">
                <div id="myCarousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img  src="<c:url value="images/slider/portrait-successful-mid-adult-doctor-with-crossed-arms.jpg"/>" class="img-fluid" alt="">
                        </div>

                        <div class="carousel-item">
                            <img src="<c:url value="images/slider/young-asian-female-dentist-white-coat-posing-clinic-equipment.jpg"/>" class="img-fluid" alt="">
                        </div>

                        <div class="carousel-item">
                            <img src="<c:url value="images/slider/doctor-s-hand-holding-stethoscope-closeup.jpg"/>" class="img-fluid" alt="">
                        </div>
                    </div>
                </div>

                <div class="heroText d-flex flex-column justify-content-center">

                    <h1 class="mt-auto mb-2">
                        Better
                        <div class="animated-info">
                            <span class="animated-item">health</span>
                            <span class="animated-item">days</span>
                            <span class="animated-item">lives</span>
                        </div>
                    </h1>

                    <p class="mb-4">Medic Care is a Bootstrap 5 Template provided by TemplateMo website. Credits go to FreePik and RawPixel for images used in this template.</p>

                    <div class="heroLinks d-flex flex-wrap align-items-center">
                        <a class="custom-link me-4" href="#about" data-hover="Learn More">Learn More</a>

                        <p class="contact-phone mb-0"><i class="bi-phone"></i> 010-020-0340</p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>

<section class="section-padding" id="about">
    <div class="container">
        <div class="row">

            <div class="col-lg-6 col-md-6 col-12">
                <h2 class="mb-lg-3 mb-3">Meet Dr. Carson</h2>

                <p>Protect yourself and others by wearing masks and washing hands frequently. Outdoor is safer than indoor for gatherings or holding events. People who get sick with Coronavirus disease (COVID-19) will experience mild to moderate symptoms and recover without special treatments.</p>

                <p>You can feel free to use this CSS template for your medical profession or health care related websites. You can <a rel="nofollow" href="http://paypal.me/templatemo" target="_blank">support us a little</a> via PayPal if this template is good and useful for your work.</p>
            </div>

            <div class="col-lg-4 col-md-5 col-12 mx-auto">
                <div class="featured-circle bg-white shadow-lg d-flex justify-content-center align-items-center">
                    <p class="featured-text"><span class="featured-number">12</span> Years<br> of Experiences</p>
                </div>
            </div>

        </div>
    </div>
</section>

<section class="gallery">
    <div class="container">
        <div class="row">

            <div class="col-lg-6 col-6 ps-0">
                <img src="<c:url value="images/gallery/medium-shot-man-getting-vaccine.jpg"/>" class="img-fluid galleryImage" alt="get a vaccine" title="get a vaccine for yourself">
            </div>

            <div class="col-lg-6 col-6 pe-0">
                <img src="<c:url value="images/gallery/female-doctor-with-presenting-hand-gesture.jpg"/>" class="img-fluid galleryImage" alt="wear a mask" title="wear a mask to protect yourself">
            </div>

        </div>
    </div>
</section>

<section class="section-padding pb-0" id="timeline">
    <div class="container">
        <div class="row">

            <h2 class="text-center mb-lg-5 mb-4">Our Timeline</h2>

            <div class="timeline">
                <div class="row g-0 justify-content-end justify-content-md-around align-items-start timeline-nodes">
                    <div class="col-9 col-md-5 me-md-4 me-lg-0 order-3 order-md-1 timeline-content bg-white shadow-lg">
                        <h3 class=" text-light">Get the vaccine</h3>

                        <p>Donec facilisis urna dui, a dignissim mauris pretium a. Quisque quis libero fermentum, tempus felis eu, consequat nibh.</p>
                    </div>

                    <div class="col-3 col-sm-1 order-2 timeline-icons text-md-center">
                        <i class="bi-patch-check-fill timeline-icon"></i>
                    </div>

                    <div class="col-9 col-md-5 ps-md-3 ps-lg-0 order-1 order-md-3 py-4 timeline-date">
                        <time>2021-07-31 Saturday</time>
                    </div>
                </div>

                <div class="row g-0 justify-content-end justify-content-md-around align-items-start timeline-nodes my-lg-5 my-4">
                    <div class="col-9 col-md-5 ms-md-4 ms-lg-0 order-3 order-md-1 timeline-content bg-white shadow-lg">
                        <h3 class=" text-light">Consulting your health</h3>

                        <p>You are fully permitted to use this template for your commercial or personal website. You are not permitted to redistribute the template ZIP file for a download purpose on any other <a href="https://www.google.com/search?q=free+css" target="_blank">Free CSS collection</a> website.</p>
                    </div>

                    <div class="col-3 col-sm-1 order-2 timeline-icons text-md-center">
                        <i class="bi-book timeline-icon"></i>
                    </div>

                    <div class="col-9 col-md-5 pe-md-3 pe-lg-0 order-1 order-md-3 py-4 timeline-date">
                        <time>2021-07-15 Thursday</time>
                    </div>
                </div>

                <div class="row g-0 justify-content-end justify-content-md-around align-items-start timeline-nodes">
                    <div class="col-9 col-md-5 me-md-4 me-lg-0 order-3 order-md-1 timeline-content bg-white shadow-lg">
                        <h3 class=" text-light">Certified Nurses</h3>

                        <p>Phasellus eleifend, urna interdum congue viverra, arcu neque ultrices ligula, id pulvinar nisi nibh et lacus. Vivamus gravida, ipsum non euismod tincidunt, sapien elit fermentum mi, quis iaculis enim ligula at arcu.</p>
                    </div>

                    <div class="col-3 col-sm-1 order-2 timeline-icons text-md-center">
                        <i class="bi-file-medical timeline-icon"></i>
                    </div>

                    <div class="col-9 col-md-5 ps-md-3 ps-lg-0 order-1 order-md-3 py-4 timeline-date">
                        <time>2021-06-28 Monday</time>
                    </div>
                </div>

                <div class="row g-0 justify-content-end justify-content-md-around align-items-start timeline-nodes my-lg-5 my-4">
                    <div class="col-9 col-md-5 ms-md-4 ms-lg-0 order-3 order-md-1 timeline-content bg-white shadow-lg">
                        <h3 class=" text-light">Covid-19 Hospitals</h3>

                        <p class="mb-0 pb-0">Fusce vestibulum euismod nulla sed ultrices. Praesent rutrum nulla vel sapien euismod, quis tempus dui placerat.</p>

                        <p>Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Maecenas faucibus mollis interdum. Donec ullamcorper nulla non metus auctor fringilla</p>
                    </div>

                    <div class="col-3 col-sm-1 order-2 timeline-icons text-md-center">
                        <i class="bi-globe timeline-icon"></i>
                    </div>

                    <div class="col-9 col-md-5 pe-md-3 pe-lg-0 order-1 order-md-3 py-4 timeline-date">
                        <time>2021-05-30 Sunday</time>
                    </div>
                </div>

                <div class="row g-0 justify-content-end justify-content-md-around align-items-start timeline-nodes">
                    <div class="col-9 col-md-5 me-md-4 me-lg-0 order-3 order-md-1 timeline-content bg-white shadow-lg">
                        <h3 class=" text-light">Freelance Nursing</h3>

                        <p>If you need a working contact form that submits email to your inbox, please <a rel="nofollow" href="https://templatemo.com/contact" target="_parent">visit our contact page</a> for more information.</p>
                    </div>

                    <div class="col-3 col-sm-1 order-2 timeline-icons text-md-center">
                        <i class="bi-person timeline-icon"></i>
                    </div>

                    <div class="col-9 col-md-5 ps-md-3 ps-lg-0 order-1 order-md-3 py-4 timeline-date">
                        <time>2021-05-18 Tuesday</time>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>

<section class="section-padding pb-0" id="reviews">
    <div class="container">
        <div class="row">

            <div class="col-12">
                <h2 class="text-center mb-lg-5 mb-4">Our Patients</h2>

                <div class="owl-carousel reviews-carousel">

                    <figure class="reviews-thumb d-flex flex-wrap align-items-center rounded">
                        <div class="reviews-stars">
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star"></i>
                        </div>

                        <p class="text-primary d-block mt-2 mb-0 w-100"><strong>Best Health Care</strong></p>

                        <p class="reviews-text w-100">Phasellus ligula ante, tempus ac imperdiet ut, mattis ac nibh. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>

                        <img src="<c:url value="images/reviews/beautiful-woman-face-portrait-brown-background.jpeg"/>" class="img-fluid reviews-image" alt="">

                        <figcaption class="ms-4">
                            <strong>Marie</strong>

                            <span class="text-muted">Patient</span>
                        </figcaption>
                    </figure>

                    <figure class="reviews-thumb d-flex flex-wrap align-items-center rounded">
                        <div class="reviews-stars">
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star"></i>
                        </div>

                        <p class="text-primary d-block mt-2 mb-0 w-100"><strong>Doctor cares everyone!</strong></p>

                        <p class="reviews-text w-100">Donec in elementum orci, nec posuere ligula. Quisque vulputate diam et ullamcorper ullamcorper. Pellentesque vestibulum neque at leo fermentum mattis.</p>

                        <img src="<c:url value="images/reviews/senior-man-wearing-white-face-mask-covid-19-campaign-with-design-space.jpeg"/>" class="img-fluid reviews-image" alt="">

                        <figcaption class="ms-4">
                            <strong>Ben Walker</strong>

                            <span class="text-muted">Recovered</span>
                        </figcaption>
                    </figure>

                    <figure class="reviews-thumb d-flex flex-wrap align-items-center rounded">
                        <div class="reviews-stars">
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                        </div>

                        <p class="text-primary d-block mt-2 mb-0 w-100"><strong>Great services!</strong></p>

                        <p class="reviews-text w-100">Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec sit amet velit vitae purus aliquam efficitur.</p>

                        <img src="<c:url value="images/reviews/portrait-british-woman.jpeg"/>" class="img-fluid reviews-image" alt="">

                        <figcaption class="ms-4">
                            <strong>Laura Zono</strong>

                            <span class="text-muted">New Patient</span>
                        </figcaption>
                    </figure>

                    <figure class="reviews-thumb d-flex flex-wrap align-items-center rounded">
                        <div class="reviews-stars">
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star-fill"></i>
                            <i class="bi-star"></i>
                            <i class="bi-star"></i>
                        </div>

                        <p class="text-primary d-block mt-2 mb-0 w-100"><strong>Best Advices</strong></p>

                        <p class="reviews-text w-100">Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Maecenas faucibus mollis interdum. Donec ullamcorper nulla non metus auctor fringilla.</p>

                        <img src="<c:url value="images/reviews/woman-wearing-mask-face-closeup-covid-19-green-background.jpeg"/>" class="img-fluid reviews-image" alt="">

                        <figcaption class="ms-4">
                            <strong>Rosey</strong>

                            <span class="text-muted">Almost Recovered</span>
                        </figcaption>
                    </figure>

                </div>
            </div>

        </div>
    </div>
</section>

<section class="section-padding" id="booking">
    <div class="container">
        <div class="row">

            <div class="col-lg-8 col-12 mx-auto">
                <div class="booking-form">

                    <h2 class="text-center mb-lg-3 mb-2">Book an appointment</h2>

                    <form role="form" action="#booking" method="post">
                        <div class="row">
                            <div class="col-lg-6 col-12">
                                <input type="text" name="name" id="name" class="form-control" placeholder="Full name" required>
                            </div>

                            <div class="col-lg-6 col-12">
                                <input type="email" name="email" id="email" pattern="[^ @]*@[^ @]*" class="form-control" placeholder="Email address" required>
                            </div>

                            <div class="col-lg-6 col-12">
                                <input type="telephone" name="phone" id="phone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" class="form-control" placeholder="Phone: 123-456-7890">
                            </div>

                            <div class="col-lg-6 col-12">
                                <input type="date" name="date" id="date" value="" class="form-control">

                            </div>

                            <div class="col-12">
                                <textarea class="form-control" rows="5" id="message" name="message" placeholder="Additional Message"></textarea>
                            </div>

                            <div class="col-lg-3 col-md-4 col-6 mx-auto">
                                <button type="submit" class="form-control" id="submit-button">Book Now</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</section>
