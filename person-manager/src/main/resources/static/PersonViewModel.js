var PersonModel = function(persons) {
  var self = this;
  self.persons = ko.observableArray(persons);
  self.selectedItems = ko.observableArray([]);  
  
  self.addPerson = function() {
    self.persons.push({
      firstName: "",
      lastName: "",
      age: 0,
      favouriteColor: "",
      hobbies: ko.observableArray([]),
      hobbyToAdd: ko.observable(""),
      selectedItems: ko.observableArray([])

    });
  };

  self.addHobby = function (person) {
        // Prevent blanks and duplicates
    if ((person.hobbyToAdd() != "") && (person.hobbies.indexOf(person.hobbyToAdd()) < 0)){
        person.hobbies.push(person.hobbyToAdd());
    }
    person.hobbyToAdd(""); // Clear the text box
  };

  self.removeSelected = function (person) {
        person.hobbies.removeAll(person.selectedItems());
        person.selectedItems([]); // Clear selection
    };
 
    self.sortItems = function(person) {
        person.hobbies.sort();
    };

  self.removePerson = function(person) {
    self.persons.remove(person);
  };

  self.savePerson = function(person) {
    var person = ko.observable({person : [mapToPersonEntityFormat(person)]});
    alert("Could now transmit to server: " + ko.utils.stringifyJson(person));
    $.ajax({
    	type: "POST",
        url: "http://localhost:8080/api/persons/person",
        data: ko.utils.stringifyJson(person),
        contentType: "application/json",
        dataType: "json"
    }).then(response => {
    	console.log(response);
    });
    // To actually transmit to server as a regular form post, write this: ko.utils.postJson($("form")[0], self.gifts);
  };
};

var mapToPersonEntityFormat = (person) => {
	return {
		first_name : person.firstName,
		last_name : person.lastName,
		age : person.age,
		favourite_colour : person.favouriteColor,
		hobby : person.hobbies()
	};
};

$.ajax({
    url: "http://localhost:8080/api/persons"
}).then(function(response) {
	var persons = [];
   if (response.person && response.person.length > 0) {
	   var index = 0;
	   response.person.forEach(per => {
		   var persObj = {
						    firstName: per.first_name,
						    lastName: per.last_name,
						    age: per.age,
						    favouriteColor: per.favourite_colour,
						    hobbies: ko.observableArray(per.hobby),
						    hobbyToAdd: ko.observable(""),
						    selectedItems: ko.observableArray([])
		   				}
		   persons[index] = persObj;
		   index++;
	   });
   }
   var viewModel = new PersonModel(persons);
   ko.applyBindings(viewModel);
});

