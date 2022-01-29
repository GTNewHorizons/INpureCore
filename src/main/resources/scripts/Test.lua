out:print("Testing internal loading and libraries...")

local class = require 'middleclass'

test = class('test')

function test:initialize()
    self.something = "Doing stuff!"
end

function test:doStuff()
    out:print(self.something)
end

function test:doStuffWithArgs(arg1, arg2)
    out:print(arg1 .. " and " .. arg2)
end

testInstance = test:new()

