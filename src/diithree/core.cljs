(ns diithree.core)

;;
;;
;; A wrapper for d3. 
;;   A work in progress.
;;
;;   based off of https://github.com/ibdknox/jayq/blob/master/src/jayq/core.cljs

(defn select
  ([this s]
   (.select this s))
  ([s]
   (select js/d3 s)))

(defn select-all
  ([this s]
   (.selectAll this s))
  ([s]
   (select-all js/d3 s)))

(defn enter [this]
  (.enter this))

(def d3type (.-selection js/d3))

(extend-type d3type

  ISeqable
  (-seq [this] (when (.node this)
                 this))
  ISeq
  (-first [this] (.node this))
  (-rest [this] (if (> (count this) 1)
                  (.slice (.nodes this) 1)
                  (list)))

  ICounted
  (-count [this] (.size this))

  IIndexed
  (-nth
    ([this n]
     (when (<  n (count this))
       (.slice (.nodes this) n (inc n))))
    ([this n not-found]
     (if (< n (count this))
       (.slice (.nodes this) n (inc n))
       (if (undefined? not-found)
         nil
         not-found))))

  ISequential

  ILookup
  (-lookup
    ([this k]
     (or (.slice (.nodes this) k (inc k)) nil))
    ([this k not-found]
     (-nth this k not-found)))

  IReduce
  (-reduce
    ([this f]
     (ci-reduce this f))
    ([this f start]
     (ci-reduce this f start))))


(def classed
  (fn
    ([this, c]
     (.classed this c))
    ([this, c, d]
     (.classed this c d))))


(defn datum
  ([this]
   (.datum this))
  ([this, d]
   (.datum this d)))

(defn data
  ([this]
   (.data this))
  ([this, d]
   (.data this d)))

(defn append [this s]
  (.append this s))

(defn attr
  ([this, s]
   (.attr this s))
  ([this, s v]
   (.attr this s v)))

(defn text [selection text]
  (.text selection text))

(defn on [this s f]
  (.on this s f))

(defn remove [this]
  (.remove this))

(defn interrupt [this]
  (.interrupt this))

(defn transition [this]
  (.transition this))

(defn ease [this v]
  (.ease this v))

(defn duration [this t]
  (.duration this t))

(defn delay [this t]
  (.delay this t))
