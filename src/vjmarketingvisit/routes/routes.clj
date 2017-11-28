(ns vjmarketingvisit.routes.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [net.cgrand.enlive-html :refer [deftemplate defsnippet] :as html]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [vjmarketingvisit.db :as db]
            [noir.io :as io]
            [clojure.java.io :as cio]
            [clojure.java.jdbc :as jdbc]))

;;helper function
(defn pic-upd [mp ky n jenis uuid]
  (if (not (nil? ((keyword (str ky n)) mp)))
    (do
      (jdbc/insert! db/dbase :person 
          {:nama ((keyword (str ky n)) mp)
           :jabatan ((keyword (str "jabatan" ky n)) mp)
           :telp ((keyword (str "telp" ky n)) mp)
           :jenis jenis
           :uuid uuid})
      (pic-upd mp ky (+ n 1) jenis uuid))))

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
  (POST "/send-action" {params :params}
  	(let [uuid (str (db/squuid))]
  		(do
  	  		(jdbc/insert! db/dbase :sekolah 
  	  	    	{:uuid uuid
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
  	  		   	 :tandatangan (:filename (:ttpic params))
  	  		   	 :capsekolah (:filename (:capsekolah params))
  	  		   	 :expensereport (:filename (:expensereport params))
  	  		   	 :controllabletime (:filename (:ctworksheet params))
  	  		   	 :dailyactivity (:filename (:dailyactivity params))
  	  		   	 :dataflyer (:filename (:dataflyering params))
  	  		   	 :isvisited (:filename (:isvisited params))})
          (pic-upd params "pic" 1 "PIC" uuid)
          (pic-upd params "cp" 1 "Contact Person" uuid)
          (pic-upd params "mk" 1 "Mantan Ketua Osis" uuid)
  			  (io/create-path (str "resources/data/" uuid) true)
    			(io/upload-file (str "resources/data/" uuid) (:ttpic params))
    			(io/upload-file (str "resources/data/" uuid) (:capsekolah params))
    			(io/upload-file (str "resources/data/" uuid) (:expensereport params))
    			(io/upload-file (str "resources/data/" uuid) (:ctworksheet params))
    			(io/upload-file (str "resources/data/" uuid) (:dailyactivity params))
    			(io/upload-file (str "resources/data/" uuid) (:dataflyering params)))))
  (route/not-found "Not Found"))
