(ns vjmarketingvisit.routes.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [net.cgrand.enlive-html :refer [deftemplate defsnippet] :as html]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [vjmarketingvisit.db :as db]
            [clojure.java.jdbc :as jdbc]))

;snippet
(defsnippet adddata "public/adddata.html"
	[:div#adddata]
	[]
	[:form#sendform] (html/append (html/html-snippet (anti-forgery-field))))

;template
(deftemplate indexpage "public/index.html"
	[snippet]
	[:div#content] (html/content snippet))

(defroutes app-routes
  (GET "/" [] 
  	(indexpage (adddata)))
  (GET "/send-action" {params :params}
  	(jdbc/insert! db/dbase :sekolah 
    	{:uuid (str (db/squuid))
   	 	 :nama (:nama params)
	   	 :alamat (:alamat params)
	   	 :kota (:kota params)
	   	 :telp (:telp params)
	   	 :kepalasekolah (:kepalasekolah params)
	   	 :humas (:humas params)
	   	 :kodepos (:kodepos params)
	   	 :website (:website params)
	   	 :sabtungajar (:sabtungajar params)
	   	 :waktupresentasi (:waktupresentasi params)
	   	 :lineid (:lineid params)
	   	 :twittersekolah (:twittersekolah params)
	   	 :twitterosis (:twitterosis params)
	   	 :twitterevent (:twitterevent params)
	   	 :koransekolah (:koransekolah params)
	   	 :jmlhguru (:jumlahguru params)
	   	 :jmlhsiswa (:jumlahsiswa params)
	   	 :notes (:notes params)
	   	 :tandatangan (:ttpic params)
	   	 :capsekolah (:capsekolah params)
	   	 :expensereport (:expensereport params)
	   	 :controllabletime (:ctworksheet params)
	   	 :dailyactivity (:dailyactivity params)
	   	 :dataflyer (:dataflyering params)
	   	 :isvisited (:isvisited params)}))
  (route/not-found "Not Found"))
