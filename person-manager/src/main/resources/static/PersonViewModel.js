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
      selfRef: "",
      hobbyToAdd: ko.observable(""),
      selectedItems: ko.observableArray([]),
      editMode: ko.observable(true)

    });
  };
  
  self.edit = function (person) {
	  person.editMode(true);
  }

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
    if (person.selfRef && person.selfRef !== "") {
    	$.ajax({
        	type: "DELETE",
            url: person.selfRef
        }).then(response => {
        	console.log(response);
        });
    }
  };

  self.savePerson = function(person) {
    var personData = ko.observable({person : [mapToPersonEntityFormat(person)]});
    if (person.selfRef && person.selfRef !== "") {
    	$.ajax({
        	type: "PUT",
            url: person.selfRef,
            data: ko.utils.stringifyJson(personData),
            contentType: "application/json",
            dataType: "json"
        }).then(response => {
        	person.editMode(false);
        });
    	
    } else {
    	$('#overlay').fadeIn();
    	$.ajax({
        	type: "POST",
            url: "/api/persons/person",
            data: ko.utils.stringifyJson(personData),
            contentType: "application/json",
            dataType: "json"
        }).then(response => {
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
        							    selfRef: per.selfRef,
        							    hobbyToAdd: ko.observable(""),
        							    selectedItems: ko.observableArray([]),
        							    editMode: ko.observable(false)
        			   				}
        			   persons[index] = persObj;
        			   index++;
        		   });
        	   }
        	   self.persons([]);
        	   self.persons(persons);
        	   $('#overlay').fadeOut();
        });
    }
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

$('#overlay').fadeIn();

$.ajax({
    url: "/api/persons"
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
						    selfRef: per.selfRef,
						    hobbyToAdd: ko.observable(""),
						    selectedItems: ko.observableArray([]),
						    editMode: ko.observable(false)
		   				}
		   persons[index] = persObj;
		   index++;
	   });
   }
   var viewModel = new PersonModel(persons);
   ko.applyBindings(viewModel);
   $('#overlay').fadeOut();
});

