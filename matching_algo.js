
                                 //helpers
// const returned_range = []
//                                
// const range = function(start, stop) {
//     while (start <= stop){
//          returned_range.push(start)
//          start+=1

//     }

//     return returned_range
// }

function getRandomIntInclusive(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1) + min); //The maximum is inclusive and the minimum is inclusive
}
  
function generateName(gender){
    const male_name = ['Jerry', 'David', 'Jason', 'Eric', 'Charles', 'Stephen', 'Douglas', 'Gabriel', 'Christopher', 'James', 'Frederick', 'Gerald', 'Roger', 'Gary', 'Virgil', 'Richard', 'Thomas', 'John', 'Eddie', 'Robert', 'Earl', 'Andrew', 'Jerome', 'Peter', 'Frank', 'Andrea', 'Joe', 'Gene', 'Kenneth', 'Randy', 'Larry', 'Joshua', 'Derrick', 'Shawn', 'Domenic', 'Edward', 'Harvey', 'William', 'Lucas', 'Jermaine', 'Kevin', 'Rodney', 'Chauncey', 'Martin', 'Ralph', 'Zachary', 'Ronald', 'Craig', 'George', 'Angel', 'Efren', 'Abel', 'Jose', 'Anthony', 'Stanley', 'Roy', 'Ron', 'Eduardo', 'Danny', 'Paul', 'Mark', 'Arthur', 'Joel', 'Rudolph', 'Cory', 'Charlie', 'Scott', 'Matthew', 'Philip', 'Dustin', 'Maurice', 'Leonard', 'Sam', 
    'Geoffrey', 'Alton', 'Ernesto', 'Arnold', 'Allen', 'Samuel', 'Hugh', 'Stewart', 'Jeff', 'Edmund', 'Lawrence', 'Hugo', 'Henry', 'Michael', 'Donald', 'Johnny', 'Jesus', 'Sean', 'Joseph', 'Norman', 'Raul', 'Curtis', 'Raymond', 'Brian', 'Delmar', 'Dwain', 'Manuel', 'Al', 'Tracey', 'Jeffry', 'Randall', 'Marvin', 'Timothy', 'Leroy', 'Leon', 'Alberto', 'Russell', 'Dan', 'Harley', 'Steven', 'Carl', 'Justin', 'Bennett', 'Tyrone', 'Rufus', 'Rickey', 'Ramon', 'Barney']

    const female_name = ['Elayne', 'Barbara', 'Lora', 'Kathy', 'Alisha', 'Katherine', 'Gloria', 'Ericka', 'Isabella', 'Lois', 'Laura', 'Marie', 'Artie', 'Constance', 'Roberta', 'Kari', 'Charlene', 'Tracy', 'Tiffany', 'Carolyn', 'Sandra', 'Valencia', 'Mary', 'Deborah', 'Connie', 'Margaret', 'Samantha', 'Sarah', 'Monica', 'Ashley', 'Dionne', 'Clarice', 'Emma', 'Sonia', 'Michael', 'Ann', 'Diane', 'Pauline', 'Rose', 'Debra', 'Maryjane', 'Alexandra', 'Tanja', 'Lori', 'Norma', 'Karen', 'Gertrude', 'Elizabeth', 'Delores', 'Rebecca', 'Yvette', 'Breann', 'Patricia', 'Kelli', 'Cora', 'Elsie', 'Tina', 'Jill', 'Kirsten', 'Katharine', 'Lisa', 'Terry', 'Isabel', 'Georgia', 'Michelle', 'Elsa', 'Tamara', 'Jessica', 'Sherri', 'Alison', 'Peggy', 'Della', 'Jacquelyn', 'Sharon', 'Amelia', 'Patti', 'Lindsay', 'Susan', 'Lovella', 'Betty', 'Maude', 'Bertha', 'Annette', 'Courtney', 'Celina', 'Celestina', 'Nina', 'Teresita', 'Judith', 'Tammy', 'Christy', 'Marguerite', 'Rhea', 'Florence', 'Lola', 'Lorraine', 'Dorothy', 'Andrea', 'Renee', 'Christina', 'Julie', 'Brittney', 'Lillie', 'Beverly', 'Santos', 'Rita', 'Laurie', 'Donna', 'June', 'Kristin', 'Antonietta', 'Irma', 'Nora', 'Anita', 'Vera', 'Margarita', 'Josephine', 'Louise', 'Dawn', 'Marlene', 'Latoya', 'Carol', 'Ruby', 'Cheryl', 'Angelita', 'Bernice', 'Rosemary', 'Gladys', 'Rebekah', 'Lauren', 'Jennifer', 'Kimberly', 'Deloris', 'Wanda', 'Joanne', 'Suzan', 'Carmen', 'Ruth', 'Elisabeth', 'Phyllis', 'Pam', 'Krista', 'Shirley', 'Deetta', 'Darleen', 'Daisy', 'Brenda', 'Eugenia', 'Willa', 'Sally', 'Ethel', 'Joyce', 'Vicki', 'Martina', 'Diann']

    const tmp_gen_picker = getRandomIntInclusive(0, male_name.length-1)
    
    if (gender.toLowerCase() === 'male'){
        return male_name[tmp_gen_picker]
    }
    else if (gender.toLowerCase() === 'female'){
        return female_name[tmp_gen_picker]
    }
}

const users_container = []
const male_bucket = []
const female_bucket = []


const interest = ['men', 'women']
const gender = ['male', 'female']
// const zodiac_sign = ['virgo', 'leo', 'Taurus', 'pisces',
// 'Gemini', 'Libra', 'Scorpio', 'sagittarius', 'Aquarius', 'pisces',
// 'Aries', 'cancer'
// ]
const zodiac_sign = ['virgo', 'leo', 'Taurus']

// const age = [...range(18, 100)]

let interest_val
for (let y = 0; y < 15; y++){
    const gender_val = getRandomIntInclusive(0, 1)
    const zodiac_picker = getRandomIntInclusive(0, (zodiac_sign.length-1))
    let rand_name = generateName(gender[gender_val])
    if (rand_name === undefined){
        rand_name = 'tom'
    }
    const gender_vals = gender[gender_val]
    if (gender_vals === 'male'){
   
        interest_val = 'female'
    }
    else{
        interest_val = 'male'
    }
    const user_pref = {name : rand_name, age : getRandomIntInclusive(18, 100), 
                    gender: gender_vals, interest: interest_val,
                    zodiac_sign: zodiac_sign[zodiac_picker]
                    }
    users_container.push(user_pref)
    
}


users_container.forEach(function(item){
    if (item.gender === 'male'){
        male_bucket.push(item)
    }
    else{
        female_bucket.push(item)
    }
})
    


// // const male_bucket = [users for users in users_container if users.get('gender') == 'male']
// // const female_bucket = [users for users in users_container if users.get('gender') == 'female']
const returned_range = []
                                //helpers
const range = function(start, stop) {
    while (start <= stop){
         returned_range.push(start)
         start+=1

    }

    return returned_range
}



const sortRankedUsers = function(ranked_users){
    ranked_users.sort(function(a, b){

        if (a.closest_match_rank > b.closest_match_rank){
            return -1
        }else if (b.closest_match_rank > a.closest_match_rank){
            return 1
        }else{
            return 0
        }
    })
}


let rank = 0
let ranged_age = 0
let temp_storage = []
let end = [] 
male_bucket.forEach(function(male_item, index){
    female_bucket.forEach(function(female_item, index){

        ranged_age =  range(female_item.age-2, female_item.age+2)
        
        rank = 0
        if (male_item.zodiac_sign === female_item.zodiac_sign){

            rank+=1
            temp_storage.push({female_name: female_item, female_rank: rank })
            
        }

        
        if (male_item.age<female_item.age-2 && female_item.age+2 < male_item.age){
          rank+=1
          temp_storage.push({female_name: female_item, female_rank: rank })
            
        }
        else{
          temp_storage.push({female_name: female_item, female_rank: rank })
        }

    })

    temp_storage.sort((a, b) => (a.color > b.color) ? 1 : -1)
    male_item.ranked = JSON.parse(JSON.stringify(temp_storage))

    end = JSON.parse(JSON.stringify(temp_storage));
    
    
    // male_item.ranked_users = end
    temp_storage.length = 0
    
    rank = 0

})

console.log(' -------------------------------------------------------------------------------- ')
console.log('male-match', male_bucket)

// male_bucket.forEach(function(value){
  
//   console.log('men name: ', value.name, 'men age', value.age, 'rank',value.ranked) 
//   console.log(' ')
// })
console.log(' -------------------------------------------------------------------------------- ')
