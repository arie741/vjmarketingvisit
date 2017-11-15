(ns vjmarketingvisit.db
  (:require [clojure.java.jdbc :as jdbc]
  			[clj-postgresql.core :as pg]))

(def dbase (pg/pool :host "localhost:5432"
                  :user "vjmarketingvisit"
                  :dbname "vjmarketingvisit"
                  :password "vjmarketingvisit2000"))

;;helper functions

(defn squuid []
  (let [uuid (java.util.UUID/randomUUID)
        time (System/currentTimeMillis)
        secs (quot time 1000)
        lsb (.getLeastSignificantBits uuid)
        msb (.getMostSignificantBits uuid)
        timed-msb (bit-or (bit-shift-left secs 32)
                          (bit-and 0x00000000ffffffff msb))]
    (java.util.UUID. timed-msb lsb)))

;;queries
(defn searchs [sekolah]
	(jdbc/query dbase [(str "select * from sekolah where nama = '" sekolah "'")]))
