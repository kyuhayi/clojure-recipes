(ns pedestal-hello-world.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [pedestal-hello-world.service :as service]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(deftest home-page-test
  (is (=
       (:body (response-for service :get "/"))
       "<html> <head>\r  <title>Home page</title>\r</head>\r  <body>\r    <h1>This is my first Pedestal webapp</h1>\r  </body>\r</html>\r"))
  (is (=
       (:headers (response-for service :get "/"))
       {"Content-Type" "text/html"
        "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
        "X-Frame-Options" "DENY"
        "X-Content-Type-Options" "nosniff"
        "X-XSS-Protection" "1; mode=block"})))


(deftest about-page-test
  (is (.contains
       (:body (response-for service :get "/about"))
       "Clojure 1.7"))
  (is (=
       (:headers (response-for service :get "/about"))
       {"Content-Type" "text/html;charset=UTF-8"
        "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
        "X-Frame-Options" "DENY"
        "X-Content-Type-Options" "nosniff"
        "X-XSS-Protection" "1; mode=block"})))

