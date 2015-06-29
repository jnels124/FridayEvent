(ns f-it-up-friday.react.templates
  (:require [f-it-up-friday.util.io :as io]))

(def cwd (System/getProperty "user.dir"))

(def base-component
  "'use strict';

const React = require('react');

const MyComponent = React.createClass({
    propTypes: {
    },
    render() {
        return (
                <div>
                </div>
        );
    }
});

module.exports = MyComponent;")

(def base-test
  "'use strict';

const expect = require('chai').expect,
    React = require('react'),
    TestUtils = require('react/addons').addons.TestUtils,
    simple = require('simple-mock'),
    mock = require('mock-require');

require('testdom')('<html><body/></html>');


describe('MySpec',  () => {

    beforeEach(() => {});

    afterEach( () => {});

    it('should ...',  () => {

    });
});")

(defn create-component [options]
  (println "Creating component and Test files...")
  (io/write-react-template (:create options) base-component :jsx)
  (io/write-react-template (str (:create options) "_test") base-component :js))

(defn create-test [options]
  (println "called react/test"))

(defn create-action [options]
  (println "called react/action"))