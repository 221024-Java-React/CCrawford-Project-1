// Challenge 1
const calcAge = (years = 0) => { return `Days old: ${years * 365}` }

console.log(calcAge(65));
console.log(calcAge(0));
console.log(calcAge(20));

// Challenge 2
const points = (twoPoints = 0, threePoints = 0) => { return `Points scored: ${(twoPoints * 2) + (threePoints * 3)}` }

console.log(points(1, 1));
console.log(points(7, 5));
console.log(points(38, 8));

// Challenge 3
const reverse = (array = []) => { return array.reverse() }

console.log(reverse([1, 2, 3, 4]));
console.log(reverse([9, 9, 2, 3, 4]));
console.log(reverse([]));

// Challenge 4
const votes = {
    upvotes: 0,
    downvotes: 0
}
const getVoteCount = (votes) => { return votes.upvotes - votes.downvotes }

console.log(getVoteCount({ upvotes: 13, downvotes: 0 }));
console.log(getVoteCount({ upvotes: 2, downvotes: 33 }));
console.log(getVoteCount({ upvotes: 132, downvotes: 132 }));

// Challenge 5
const newWord = (word) => { return word.slice(1) }

console.log(newWord("apple"));
console.log(newWord("cherry"));
console.log(newWord("plum"));

// Challenge 6
const arrayToString = (array = []) => {
    let returnString = "";
    for(const i of array){
        returnString += i;
    }
    return returnString;
}

console.log(arrayToString([1, 2, 3, 4, 5, 6]));
console.log(arrayToString(["a", "b", "c", "d", "e", "f"]));
console.log(arrayToString([1, 2, 3, "a", "s", "dAAAA"]));

// Challenge 7
const findIndex = (array = [], item = "") => { return array.findIndex((x) =>  x === item) }

console.log(findIndex(["hi", "edabit", "fgh", "abc"], "fgh"));
console.log(findIndex(["Red", "blue", "Blue", "Green"], "blue"));
console.log(findIndex(["a", "g", "y", "d"], "d"));
console.log(findIndex(["Pineapple", "Orange", "Grape", "Apple"], "Pineapple"));

// Challenge 8
const absolute = (number = 0) => { return number > 0 ? number : 0 - number }

console.log(absolute(-1.217197940));
console.log(absolute(-12.1320));
console.log(absolute(-544.0));
console.log(absolute(4666));
console.log(absolute(-3.14));
console.log(absolute(250));

// Challenge 9
let ati = {
    needs: 0,
    wants: 0,
    savings: 0
}

const fiftyThirtyTwenty = (number = 0) => { return ati = {needs: number * .5, wants: number * .3, savings: number * .2} }

console.log(fiftyThirtyTwenty(10000));
console.log(fiftyThirtyTwenty(50000));
console.log(fiftyThirtyTwenty(13450));

// Challenge 10
const city = {
    name: "",
    population: "",
    continent: ""
}

const cityFacts = (city) => { return `${city.name} has a population of ${city.population} and is situated in ${city.continent}` }

console.log(cityFacts({
    name: "Paris",
    population: "2,140,526",
    continent: "Europe"
  }));
console.log(cityFacts({
    name: "Tokyo",
    population: "13,929,286",
    continent: "Asia"
  }));