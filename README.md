## LB : LEVELS BEYOND TEMPLATE CREATOR
### brought to you by *f-it-up-friday*

-----

A CLI template generator for Angular and React.

Think:

```
$ rails new controller 
or 
$ yo new react-fire-app 
```
now you can :

`$ lb -c MainController,scope,q,backend -d myDirective,something,else -f davidController -m tester
`
	


which gives you a controller like this:

```
angular.module('tester').controller('MainController', ['scope', 'q', 'backend', function (scope, q, backend) {}]);

```

and a directive like this:

```
angular.module('tester').directive('myDirective', ['something', 'something', 'else', function (something, something, else) {
       return {
        restrict: 'E',
        controller: 'MainController',
        scope: true,
        link: function (scope, element, attributes, controllers) {}
};
}]);
```
In files: 
*MainController-controller.js*, *MainController-directive.js* respectively.

Run **--help** for all options and flags.

---

We are looking for feedback on our cli switch name conventions.

All feedback welcome through issues.

Let us know how you would like to be able to use it.

Thanks!

@jnels124 @dviramontes


## SETUP

For angular usage :

- set enviorment variable : `$ export LBTC_FRAMEWORK=angular`

For react usage :

- set enviorment variable : `$ export LBTC_FRAMEWORK=react`

The framework will default to **angular** if none is set.

## Framework Assumptions

- **Angular v1.3**

- **React v1.3**
    - **ES6 via babel**
	- **JSX**
	- **commonjs**


## Building EXE
- add lein-bin to your lein profile 
```:plugins [[lein-bin "0.3.4"]]```
- `$ lein bin`
- `$ ./target/lb -h`
- `$ sudo chmod +x lb`
- move exe to `~/bin` or wherever you keep your exes



## Licenses

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
