# README

Steps taken to "get here"

### Chapter One - Building, Running, and the REPL
1. Create a new Clojure app using Leiningen
  1. `$ lein new app clojure-noob`
  1. open `src/clojure-noob/core.clj` and edit the print statement
  1. compile and run in terminal with `$ lein run`
1. Create and run a standalone java file to distribute and for others to run (within the ignored directory target)
  * `$ lein uberjar` - creates two new files
    * `clojure-noob-0.1.0-SNAPSHOT-standalone.jar`
    * `clojure-noob-0.1.0-SNAPSHOT.jar`
  * `$ java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar`
1. Use and play with the lein REPL
  * `$ lein repl`
  * Note that within the REPL, you are in the `clojure-noob.core` namespace. The only function defined is `-main`, and we can execute that function with `=> (-main)`
  * Also within the REPL, we can execute some basic Clojure functions:
    * `=> (+ 1 2 3 4)`
    * `=> (* 1 2 3 4)`
    * `=> (first [1 2 3 4])`
  * exit the REPL with `=> exit`

### Chapter Two - something something emacs something
1. Open Atom
1. Install Parinfer

### Chapter Three - Do Things: A Clojure Crash Course
1. general form: `(operator operand1 operand2 ... operandn)`
1. no need for commas between operands (aka arguments), commas are "turned into" whitespace
1. `if` operator

  ```clojure
  (if true
    "return true"
    "return false")
  ; => "return true"

  (if false
    "return something amazing")
  ; => nil

  (if true
    (do (println "It's TRUE!")
        "Super true.")
    (do (println "NOT TRUE.")
        "Super not true."))
  ; => It's TRUE!
  ; => "Super true."
  ```
1. `when` operator

  ```clojure
  (when true
    (println "do all these things!")
    "really, all of them.")
  ; => do all these things!
  ; => "really, all of them."
  ```

1. `nil? nil` => `true`
1. `nil` is falsy, `false` is `false`, everything else is `true` / truthy
1. `(= 1 1)` => `true`
1. `or` operator returns the first truthy value or last value

  ```clojure
  (or false nil :large_I_mean_venti :why_cant_I_just_say_large)
  ; => :large_I_mean_venti
  (or false nil)
  ; => nil
  ```

1. `and` operator returns the last truthy value or first falsy

  ```clojure
  (and :free_wifi :hot_coffee)
  ; => :hot_coffee
  (and :battleship nil false)
  ; => nil
  ```

1. bind a name to a value (like assign value to a variable, but there are no variables in Clojure)

  ```clojure
  (def awesome-peeps
    ["Elowyn Platzer Bartel" "Emily Platzer" "Luke Bartel"])
  ```

1. building up strings is different since there are no variables. No mutation!

  Ruby is cool with mutation:

  ```rb
  severity = :mild
  error_message = "OH GOD! IT'S A DISASTER! WE'RE "
  if severity == :mild
    error_message = error_message + "MILDLY INCONVENIENCED!"
  else
    error_message = error_message + "DOOOOOOOMED!"
  end
  ```

  In Clojure, we do it without mutation:

  ```clojure
  (defn error-message
    [severity]
    (str "OH GOD! IT'S A DISASTER! WE'RE "
         (if (= severity :mild)
           "MILDLY INCONVENIENCED!"
           "DOOOOOOOMED!")))

  (error-message :mild)
  ; => "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"
  ```

1. Strings
  * double quotes
  * concat via function `str` that returns new string
1. Maps (aka hashes / objects in other languages)
  * are hash maps OR sorted maps
  * keys are keywords `:first-name`
  * values are any type... strings, numbers, maps, vectors (aka arrays), or functions
  * defined by using hash literal or function:
    * `{:a 1 :b 2} ; literal`
    * `(hash-map :a 1 :b 2) ; function`
  * lookup via `get` function, returns `nil` if not found unless a default value is given. Also lookup via map as "function", keyword as argument. nested maps use `get-in` to go more than one level deep. keywords can also be used as functions to access values in an argument map.

    ```clojure
    (get {:a 1 :b 2} :b)
    ; => 2
    (get {:a 1 :b 2} :c)
    ; => nil
    (get {:a 1 :b 2} :c "unicorns")
    ; => "unicorns"
    ({:a 1 :b 2} :a)
    ; => 1
    ({:a 1 :b 2} :c "unicorns")
    ; => "unicorns"
    (get-in {:a 1 :b {:c 2}} [:b :c])
    ; => 2
    (:a {:a 1 :b 2})
    ; => 1
    ```

1. Vectors (aka arrays in other languages) - 0 indexed collections
  * elements can be of any type, or mixes of types in one vector
  * defined using literal or function
    * `[1,2,3]`
    * `(vector 1 2 3)`
  * accessed by get

    ```clojure
    (get [1 2 3] 0)
    ; => 1
    ```

  * conjoined new elements with function `conj`, elements are added to the end

    ```clojure
    (conj [1 2 3] 4)
    ; => [1 2 3 4]
    ```

1. Lists are similar to vectors. Can not use function `get` on lists. Use when want to add elements to the beginning of a list OR when you are writing a macro (?)
  * defined using literal or `list` function
    * `'(1 2 3 4)`
    * `(list 1 2 3 4)`
  * access elements with `nth` function (significantly slower than vectors and `get` function)

    ```clojure
    (nth '(1 2 3 4) 0)
    ; => 1
    ```

  * conjoined new elements with function `conj`, elements are added to the beginning

    ```clojure
    (conj '(1 2 3) 0)
    ; => (0 1 2 3)
    ```

1. Sets
---

Autogenerated README from `lein new app clojure-noob` below

---

# clojure-noob

FIXME: description

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar clojure-noob-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
