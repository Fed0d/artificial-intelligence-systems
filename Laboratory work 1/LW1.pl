male(tytos_lannister).
male(tywin_lannister).
male(kevan_lannister).
male(robert_baratheon).
male(jaime_lannister).
male(tyrion_lannister).
male(lancel_lannister).
male(joffrey_baratheon).
male(tommen_baratheon).
male(willem_lannister).
male(martin_lannister).
female(jane_marbrand).
female(joanna_lannister).
female(dorna_lannister).
female(cersei_lannister).
female(myrcella_baratheon).
female(margaery_tyrell).
female(amarea_frey).
female(janea_lannister).
female(jenna_lannister).
parent(tytos_lannister, tywin_lannister).
parent(tytos_lannister, kevan_lannister).
parent(jane_marbrand, tywin_lannister).
parent(jane_marbrand, kevan_lannister).
parent(tywin_lannister, cersei_lannister).
parent(tywin_lannister, jaime_lannister).
parent(tywin_lannister, tyrion_lannister).
parent(joanna_lannister, cersei_lannister).
parent(joanna_lannister, jaime_lannister).
parent(joanna_lannister, tyrion_lannister).
parent(kevan_lannister, lancel_lannister).
parent(kevan_lannister, willem_lannister).
parent(kevan_lannister, martin_lannister).
parent(kevan_lannister, janea_lannister).
parent(dorna_lannister, lancel_lannister).
parent(dorna_lannister, willem_lannister).
parent(dorna_lannister, martin_lannister).
parent(dorna_lannister, janea_lannister).
parent(robert_baratheon, joffrey_baratheon).
parent(robert_baratheon, myrcella_baratheon).
parent(robert_baratheon, tommen_baratheon).
parent(cersei_lannister, joffrey_baratheon).
parent(cersei_lannister, myrcella_baratheon).
parent(cersei_lannister, tommen_baratheon).
spouse(tytos_lannister, jane_marbrand).
spouse(joanna_lannister, tywin_lannister).
spouse(kevan_lannister, dorna_lannister).
spouse(robert_baratheon, cersei_lannister).
spouse(joffrey_baratheon, margaery_tyrell).
spouse(lancel_lannister, amarea_frey).
father(X, Y) :- parent(X, Y), male(X).
mother(X, Y) :- parent(X, Y), female(X).
son(X, Y) :- parent(Y, X), male(X).
daughter(X, Y) :- parent(Y, X), female(X).
grandparent(X, Y) :- parent(X, Z), parent(Z, Y).
grandmother(X, Y) :- grandparent(X, Y), female(X).
grandfather(X, Y) :- grandparent(X, Y), male(X).
sibling(X,Y) :- parent(Z,X), parent(Z,Y), X\=Y.
sister(X, Y) :- sibling(X, Y), female(X).
brother(X, Y) :- sibling(X, Y), male(X).
predecessor(X,Y) :- parent(X,Y) ; (parent(X,Z), predecessor(Z,Y)). 
cousin(X, Y) :- parent(Z, X), parent(V, Y), sibling(Z, V).  